package app.controller;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.FileNameMap;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import app.entity.BuildType;
import app.entity.Crawled;
import app.entity.CrawledInfo;
import app.entity.CrawledRating;
import app.entity.ResidenceType;
import app.entity.Source;
import app.enumerated.CurrencyEnum;
import app.scraper.LinksScraper;
import app.service.BuildTypeService;
import app.service.CrawledService;
import app.service.ResidenceTypeService;
import app.service.SourceService;

@Controller
@RequestMapping("test")
public class TestController {

	@Autowired
	private SourceService sourceService;

	@Autowired
	private CrawledService crawledService;

	@Autowired
	private ResidenceTypeService residenceTypeService;

	@Autowired
	private BuildTypeService buildTypeService;

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@GetMapping
	@ResponseBody
	public String index() {

		HashMap<String, String> payload = new HashMap<String, String>();
		payload.put("key1", "value1");
		payload.put("key2", "value2");

		String json = null;
		try {
			json = new ObjectMapper().writeValueAsString(payload);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.err.println(json);

		try {
			JsonNode jsonNode = new ObjectMapper().readTree(json);
			System.err.println(jsonNode);
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

//		ContentScraper scraper = new ContentScraper("url");

		return null;
	}

	@GetMapping("content/scraper")
	@ResponseBody
	public String contentScraper() {

		String link = "https://www.olx.bg/ad/tristaen-apartament-v-kv-karshiyaka-CID368-ID85TKH.html#374e599dda";

		Source source = sourceService.getOne(2);

		System.err.println(crawledService.isCrawledUrlAlreadySaved(link));

//		// Scrape content
//		ContentScraper contentScraper = null;
//		try {
//			contentScraper = new ContentScraper(link);
//		} catch (Exception e) {
//			logger.info("Failed to fetch page " + link);
//			logger.error(e.getMessage());
//		}

//		// Instance of AnalyzeContent class
//		AnalyzeContent analyzeContent = null;
//		try {
//			analyzeContent = (AnalyzeContent) Class.forName(source.getSourceGenerator().getContentAnalyzer())
//					.newInstance();
//		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
//			logger.error("No such analyze class found! ");
//			logger.error(e.getMessage());
//		}
//
//		// Check for page content and analyze
//		if (contentScraper.getPage() == null) {
//			logger.error("Not found page content for address " + link);
//		}
//		analyzeContent.setHtml(contentScraper.getPage());
//
//		try {
//			analyzeContent.analyze();
//		} catch (Exception e) {
//			logger.error("Failed to analyze page content for address " + link);
//			logger.error(e.getMessage());
//		}

//		// Crawl url address and return CrawledInfo object
//		CrawledInfo crawledInfo = new CrawledInfo(analyzeContent.getTitle(), analyzeContent.getDescription(),
//				analyzeContent.getKeywords(), analyzeContent.getRegion(), analyzeContent.getCurrency(),
//				analyzeContent.getPrice(), analyzeContent.getPricePerSquare(), analyzeContent.getSize(),
//				analyzeContent.getFloor(), analyzeContent.getBuildAt(), analyzeContent.getType(),
//				analyzeContent.getBuildType());
//
//		System.err.println(crawledInfo);
//
//		// Rating
//		CrawledRating crawledRating = new AnalyzeRating(crawledInfo).getCrawledRating();
//
//		// Images
//		List<CrawledImage> crawledImages = null;
//		if (analyzeContent.getImages() != null) {
//			crawledImages = null;
//		}

		ResidenceType type = residenceTypeService.findById(1);
		BuildType buildType = buildTypeService.findById(1);

		BigDecimal price = new BigDecimal("44000");
		BigDecimal pricePerSquare = new BigDecimal("440");
		Short size = new Short("100");
		Byte floor = new Byte("4");

		CrawledInfo crawledInfo = new CrawledInfo("title", "description", "keywords", "region", CurrencyEnum.EUR, price,
				pricePerSquare, size, floor, "build At", type, buildType);

		CrawledRating crawledRating = new CrawledRating(new Double("5"), new Double("5"), new Double("5"),
				new Double(5));

		// Save Crawled, Crawled, Rating
		Crawled crawled = new Crawled(link);

		crawled.setSource(source);

		crawled.setCrawledInfo(crawledInfo);
		crawledInfo.setCrawled(crawled);

		crawled.setCrawledRating(crawledRating);
		crawledRating.setCrawled(crawled);

//		crawled.setCrawledImages(crawledImages);

		crawledService.save(crawled);

		return null;
	}

	@GetMapping("/regex")
	@ResponseBody
	public String regex() {

		String str = "57 948 EUR597.40 EUR/кв.м";
		String regex = "\\d+(?:[\\.\\s]\\d+)?";

		Pattern pattern = Pattern.compile(regex, Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE | Pattern.MULTILINE);

		System.err.println(pattern.pattern());

		str = str.replaceAll("&" + "nbsp;", " ");
		str = str.replaceAll(String.valueOf((char) 160), " ");

		Matcher matcher = pattern.matcher(str);

		matcher.find();

		System.err.println(matcher.group());

		matcher.find();

		System.err.println(matcher.group());

//		while (matcher.find()) {
//			System.err.println(matcher.group());
//		}

		return null;
	}

	@GetMapping("/regex/olx")
	@ResponseBody
	public String regexOlx() {

		String url = "https://www.olx.bg/nedvizhimi-imoti/prodazhbi/apartamenti/plovdiv/?search%5Bfilter_enum_atype%5D%5B0%5D=3&search%5Bfilter_enum_atype%5D%5B1%5D=4&search%5Bfilter_enum_atype%5D%5B2%5D=mnogostaen&search%5Bfilter_enum_atype%5D%5B3%5D=mezonet&search%5Bdescription%5D=1";
		String regex = "//div[@class=\"content\"]//div[@class=\"offer-wrapper\"]//a/@href";

		LinksScraper scraper = new LinksScraper();

		scraper.setUrl(url);
		scraper.setRegex(regex);

		try {
			Set<String> listLinks = scraper.getLinks();
			System.err.println(listLinks.size());
			for (String link : listLinks) {
				System.err.println(link);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	@GetMapping("/bcrypt")
	@ResponseBody
	public String bcrypt() {

		long str = System.currentTimeMillis();

		String string = BCrypt.hashpw(String.valueOf(str), BCrypt.gensalt());

		System.err.println(string);
		System.err.println(string.length());

		return null;
	}

	@GetMapping("/image/save")
	public String image() {

		URL url = null;
		String path = "./src/main/resources/images/";
		String filename = BCrypt.hashpw(String.valueOf(System.currentTimeMillis()), BCrypt.gensalt());
		String ext = null;

		filename = filename.replaceAll("[./]", "1");

		List<String> mimeTypes = Arrays.asList(ImageIO.getReaderMIMETypes());
		for (String mimeType : mimeTypes) {
			System.err.println(mimeType);
		}

		try {

//			url = new URL("https://apollo-frankfurt.akamaized.net/v1/files/rhezr5728ttq-BG/image;s=585x461");
			url = new URL("https://www.alo.bg/user_files/a/alegre/2210552_106691129_big.jpg");

			String mimeType = url.openConnection().getHeaderField("Content-Type");

			if (!mimeTypes.contains(mimeType))
				throw new IOException("Mime type not valid!");

			ext = mimeType.substring(mimeType.lastIndexOf("/") + 1, mimeType.length());

			BufferedInputStream bis = new BufferedInputStream(url.openStream());

			FileUtils.copyInputStreamToFile(bis, new File(path + filename + "." + ext));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "test/index";
	}
	
	@GetMapping("/search/residence/type")
	@ResponseBody
	public String findResidenceTypeByKeywords() {
		
		String string = "Тристаен апартамент";
		
		ResidenceType type = residenceTypeService.findResidenceTypeByKeywords(string);
		
		System.err.println(type);
		
		return null;
	}
	
	@GetMapping("/search/build/type")
	@ResponseBody
	public String findBuildTypeByKeywords() {
		
		String string = "тухла";
		
		BuildType buildType = buildTypeService.findBuildTypeByKeywords(string);
		
		System.err.println(buildType);
		
		return null;
	}

}
