package app.scheduled;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.channels.InterruptedByTimeoutException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

import app.entity.Crawled;
import app.entity.CrawledImage;
import app.entity.CrawledInfo;
import app.entity.CrawledRating;
import app.entity.Source;
import app.enumerated.RequestTypeEnum;
import app.scraper.ContentScraper;
import app.scraper.LinksScraper;
import app.scraper.analyze.AnalyzeContent;
import app.scraper.analyze.AnalyzeRating;
import app.service.CrawledImageService;
import app.service.CrawledService;
import app.service.SourceService;

@Component
@EnableAsync
public class ScrapeScheduled {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private SourceService sourceService;

	@Autowired
	private CrawledService crawledService;

	@Autowired
	private CrawledImageService crawledImageService;

//	@Async
//	@Scheduled(fixedRate = 3600000)
	public void scheduledDeadlineTaskCheck() throws InterruptedByTimeoutException {

		// Start Report
		Long start_scraping = System.currentTimeMillis();

		logger.info("----------------------------------------------------------------------------------");
		logger.info("------------------->>>>>>>>> Start scraping <<<<<<<<<<<<--------------------------");
		logger.info("----------------------------------------------------------------------------------");

		// Get all sources
		List<Source> sources = sourceService.findByActive(true);

		// Iterate over each source
		for (Source source : sources) {

			// Fetching Source
			logger.info("Fetching source " + source);

			if (source.getSourceGenerator() == null) {
				logger.error("Missing source generator for " + source);
				logger.error("Skiping source...");
				continue;
			}

			// Get Source links as String
			Set<String> links = getAllLinks(source);
			if (links == null) {
				logger.error("No links found for source " + source);
				continue;
			} else {
				logger.info("Found " + links.size() + " Links");
			}

			// Iterate over each address
			for (String link : links) {

				// Check if link is already crawled
				if (crawledService.isCrawledUrlAlreadySaved(link)) {
					logger.info("Url address " + link + " has already been crawled!");
					continue;
				}

				// Sleep for random time to mimic human behaviour
				sleepForRandomInterval();

				// Scrape content
				ContentScraper contentScraper;
				try {
					contentScraper = new ContentScraper(link);
				} catch (Exception e) {
					logger.info("Failed to fetch page " + link);
					logger.error(e.getMessage());
					continue;
				}

				// Instance of AnalyzeContent class
				AnalyzeContent analyzeContent;
				try {
					analyzeContent = (AnalyzeContent) Class.forName(source.getSourceGenerator().getContentAnalyzer())
							.newInstance();
				} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
					logger.error("No such analyze class found! ");
					logger.error(e.getMessage());
					continue;
				}

				// Check for page content and analyze
				if (contentScraper.getPage() == null) {
					logger.error("Not found page content for address " + link);
					continue;
				}
				analyzeContent.setHtml(contentScraper.getPage());

				try {
					analyzeContent.analyze();
				} catch (Exception e) {
					logger.error("Failed to analyze page content for address " + link + " Message " + e.getMessage());
					continue;
				}

				CrawledInfo crawledInfo;
				try {
					// Crawl url address and return CrawledInfo object
					crawledInfo = new CrawledInfo(analyzeContent.getTitle(), analyzeContent.getDescription(),
							analyzeContent.getKeywords(), analyzeContent.getRegion(), analyzeContent.getCurrency(),
							analyzeContent.getPrice(), analyzeContent.getPricePerSquare(), analyzeContent.getSize(),
							analyzeContent.getFloor(), analyzeContent.getBuildAt(), analyzeContent.getType(),
							analyzeContent.getBuildType());
				} catch (Exception e) {
					logger.error("Creating Info Error : " + e.getMessage());
					continue;
				}

				CrawledRating crawledRating;
				try {
					crawledRating = new AnalyzeRating(crawledInfo).getCrawledRating();
				} catch (Exception e) {
					logger.error("Creating Rating Error : " + e.getMessage());
					continue;
				}

				// Save Crawled, Crawled, Rating
				Crawled crawled = new Crawled(link);

				crawled.setSource(source);

				crawled.setCrawledInfo(crawledInfo); // OneToOne relation
				crawledInfo.setCrawled(crawled); // OneToOne relation

				crawled.setCrawledRating(crawledRating);
				crawledRating.setCrawled(crawled);

				// Save crawled
				crawled = crawledService.save(crawled);

				// Images
				if (analyzeContent.getImages() != null) {
					processImages(crawled, analyzeContent.getImages());
				}
break;
			}
break;
		}

		// Finish Report
		Long finish_scraping = System.currentTimeMillis();

		logger.info("----------------------------------------------------------------------------------");
		logger.info("----------------------->>>>>>>>> Report <<<<<<<<<<<<------------------------------");
		logger.info("----------------------------------------------------------------------------------");
		logger.info("Started at : " + new Date(start_scraping));
		logger.info("Finished  in : " + (finish_scraping - start_scraping) / 1000);
		logger.info("----------------------------------------------------------------------------------");
		logger.info("------------------->>>>>>>>> Finish scraping <<<<<<<<<<<<-------------------------");
		logger.info("----------------------------------------------------------------------------------");
	}

	/**
	 * Fetches source content and analyze href
	 * 
	 * @param source
	 * @return List<String>
	 */
	private Set<String> getAllLinks(Source source) {

		String url = null;
		String regex = null;

		// Create links scraper class
		LinksScraper linksScraper = new LinksScraper();

		// Create empty list
		Set<String> links = new HashSet<String>();

		if (source.getSourceGenerator().getType() == RequestTypeEnum.GET) {
			url = source.getSourceGenerator().getUrl();
			regex = source.getSourceGenerator().getLinkRegex();
		} else if (source.getSourceGenerator().getType() == RequestTypeEnum.POST) {

		} else {
			return null;
		}

		// Set LinksScraper
		linksScraper.setUrl(url);
		linksScraper.setRegex(regex);

		try {
			for (String link : linksScraper.getLinks()) {
				if (!isValidUrl(link)) {
					link = sanitizeUrl(source.getUrl(), link);
				}
				links.add(link);
			}
		} catch (Exception e) {
			return null;
		}

		return links;
	}

	/**
	 * Iterate over Set<String> of images and process images.
	 * 
	 * @param images
	 * @return Set<CrawledImage>
	 */
	private Set<CrawledImage> processImages(Crawled crawled, Set<String> images) {

		// Set processed images
		Set<CrawledImage> processedImages = new HashSet<CrawledImage>();

		// Set images path
		String path = "./src/main/resources/images/" + crawled.getId() + "/";

		for (String address : images) {

			// Check if image address is valid url
			if (!isValidUrl(address))
				continue;

			// Download image
			BufferedImageWrapper image = null;
			try {
				image = saveImage(address, path);

				crawledImageService
						.save(new CrawledImage(image.getPath(), image.getFilename(), image.getExt(), crawled));
			} catch (IOException e) {
				e.printStackTrace();
				continue;
			}

		}

		return processedImages;
	}

	/**
	 * 
	 * @param address
	 * @param destination
	 * @throws IOException
	 * @throws MalformedURLException
	 * @throws URISyntaxException
	 */
	private BufferedImageWrapper saveImage(String address, String path) throws IOException {

		// Image valid url address
		URL url = new URL(address);

		// Generate random filename
		char c = (char) (new Random().nextInt(26) + 'a');
		String filename = BCrypt.hashpw(String.valueOf(System.currentTimeMillis()), BCrypt.gensalt()).replaceAll("[./]",
				String.valueOf(c));

		// Get ImageIO mime types as list
		List<String> mimeTypes = Arrays.asList(ImageIO.getReaderMIMETypes());

		// Get current image mime type
		String mimeType = url.openConnection().getHeaderField("Content-Type");

		// Check if valid image mime type
		if (!mimeTypes.contains(mimeType))
			throw new IOException("Mime type not valid!");

		// Set ext of current image
		String ext = mimeType.substring(mimeType.lastIndexOf("/") + 1, mimeType.length());

		// Open buffered input stream
		BufferedInputStream bis = new BufferedInputStream(url.openStream());

		// Copy file to destination
		FileUtils.copyInputStreamToFile(bis, new File(path + filename + "." + ext));

		// Close buffered input stream
		bis.close();

		return new BufferedImageWrapper(path, filename, ext);
	}

	/**
	 * Check if url address is valid
	 * 
	 * @param url
	 * @return Boolean
	 */
	private Boolean isValidUrl(String url) {

		try {
			new URL(url).toURI();
		} catch (MalformedURLException | URISyntaxException e) {
			return false;
		}

		return true;
	}

	/**
	 * Adds host name to url address
	 * 
	 * @param base Source url host name
	 * @param url
	 * @return String valid url adress
	 */
	private String sanitizeUrl(String base, String url) {

		try {
			URL baseUrl = new URL(base);
			URL absoluteUrl = new URL(baseUrl, url);

			return absoluteUrl.toString();
		} catch (MalformedURLException e) {
			logger.info(e.getMessage());
		}

		return null;
	}

	/**
	 * Sleep for random interval ot time
	 */
	private void sleepForRandomInterval() {
		int min = 1;
		int max = 4;

		Random random = new Random();

		try {
			TimeUnit.SECONDS.sleep(random.nextInt((max - min) + 1) + min);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Local class handling image information
	 * 
	 * @author stanislav
	 *
	 */
	class BufferedImageWrapper {

		private String path;
		private String filename;
		private String ext;

		public BufferedImageWrapper(String path, String filename, String ext) {
			this.path = path;
			this.filename = filename;
			this.ext = ext;
		}

		public String getPath() {
			return path;
		}

		public String getFilename() {
			return filename;
		}

		public String getExt() {
			return ext;
		}

	}

}
