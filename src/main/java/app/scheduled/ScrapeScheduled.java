package app.scheduled;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.channels.InterruptedByTimeoutException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import app.entity.Crawled;
import app.entity.CrawledImage;
import app.entity.CrawledInfo;
import app.entity.CrawledRating;
import app.entity.Source;
import app.scraper.ContentScraper;
import app.scraper.LinksScraper;
import app.scraper.analyze.AnalyzeContent;
import app.scraper.analyze.AnalyzeRating;
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
				logger.info("Missing source generator for " + source);
				continue;
			}

			// Get Source links as String
			List<String> links = getAllLinks(source);
			if (links == null) {
				logger.info("No links found for source " + source);
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
				ContentScraper contentScraper = new ContentScraper(link);
				if (contentScraper.getPage() == null) {
					logger.info("Failed to fetch page " + link);
					continue;
				}

				AnalyzeContent analyzeContent = null;
				try {
					analyzeContent = (AnalyzeContent) Class.forName(source.getSourceGenerator().getContentRegex())
							.newInstance();
				} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
					logger.info("No such analyze class found! " + e.getMessage());
					e.printStackTrace();
					continue;
				}

				analyzeContent.setPage(contentScraper.getPage());
				analyzeContent.analyze();

				// Crawl url address and return CrawledInfo object
				CrawledInfo crawledInfo = new CrawledInfo(analyzeContent.getTitle(), analyzeContent.getDescription(),
						analyzeContent.getKeywords(), analyzeContent.getRegion(), analyzeContent.getType(),
						analyzeContent.getCurrency(), analyzeContent.getPrice(), analyzeContent.getPricePerSquare(),
						analyzeContent.getSize(), analyzeContent.getFloor(), analyzeContent.getBuildType(),
						analyzeContent.getBuildAt());

				// Rating
				CrawledRating crawledRating = new AnalyzeRating(crawledInfo).getCrawledRating();

				// Images
				List<CrawledImage> crawledImages = null;
				if (analyzeContent.getImages() != null) {
					crawledImages = processImages(analyzeContent.getImages());
				}

				// Save Crawled, Crawled, Rating
				Crawled crawled = new Crawled(link);
				crawled.setCrawledInfo(crawledInfo);
				crawled.setCrawledRating(crawledRating);
				crawled.setCrawledImages(crawledImages);

				crawledService.save(crawled);
			}

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

	private List<String> getAllLinks(Source source) {
		String url = null;
		String regex = null;

		// Create links scraper class
		LinksScraper linksScraper = new LinksScraper();

		// Create empty list
		List<String> links = new ArrayList<String>();

		if (source.getSourceGenerator().getType().equalsIgnoreCase("GET")) {
			url = source.getSourceGenerator().getUrl();
			regex = source.getSourceGenerator().getLinkRegex();
		} else if (source.getSourceGenerator().getType().equalsIgnoreCase("POST")) {

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

		}

		return links;
	}

	private List<CrawledImage> processImages(List<String> images) {
		return null;
	}

	private Boolean isValidUrl(String url) {

		try {
			new URL(url).toURI();
		} catch (MalformedURLException | URISyntaxException e) {
			return false;
		}

		return true;
	}

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

}
