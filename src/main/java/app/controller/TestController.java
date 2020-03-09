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
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PageableDefault;
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
import app.entity.ResidenceType;
import app.entity.Source;
import app.entity.User;
import app.entity.UserCrawled;
import app.enumerated.SearchOperation;
import app.repository.specs.CrawledInfoSpecification;
import app.repository.specs.CrawledSpecification;
import app.repository.specs.SearchCriteria;
import app.scraper.ContentScraper;
import app.scraper.LinksScraper;
import app.scraper.analyze.AnalyzeContent;
import app.scraper.analyze.AnalyzeContentOlxBg;
import app.service.BuildTypeService;
import app.service.CrawledInfoService;
import app.service.CrawledService;
import app.service.ResidenceTypeService;
import app.service.SourceService;
import app.service.UserService;

@SuppressWarnings("unused")
@Controller
@RequestMapping("test")
public class TestController {

	@Autowired
	private ApplicationContext context;

	@Autowired
	private SourceService sourceService;

	@Autowired
	private CrawledService crawledService;

	@Autowired
	private CrawledInfoService crawledInfoService;

	@Autowired
	private ResidenceTypeService residenceTypeService;

	@Autowired
	private BuildTypeService buildTypeService;

	@Autowired
	private UserService userService;

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

		String link = "https://www.olx.bg/ad/prodavam-3-staen-apartamentna-ul-karlaka-2-CID368-ID86agm.html#1927cbea9d;promoted";

		Source source = sourceService.getOne(2);

		// Scrape content
		ContentScraper contentScraper = null;
		try {
			contentScraper = new ContentScraper(link);
		} catch (Exception e) {
			logger.info("Failed to fetch page " + link);
			e.printStackTrace();
		}

		AnalyzeContent analyzeContent = (AnalyzeContent) context.getBean(source.getSourceGenerator().getBean());

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

	@GetMapping("/bean")
	@ResponseBody
	public String findBean() {

//		String[] beans = context.getBeanDefinitionNames();
//        Arrays.sort(beans);
//        for (String bean : beans) 
//        {
//        	System.err.println(bean);
////            System.out.println(bean + " of Type :: " + context.getBean(bean).getClass());
//        }

		System.err.println(context.getBean("analyzeContentOlxBg"));

//		String str = "app.scraper.analyze.AnalyzeContentOlxBg";
//		
//		Object content = context.getBean();
//		
//		System.err.println(content);

		return null;
	}

	@GetMapping("/sanitize/url")
	@ResponseBody
	public void sanitizeUrl() {
		String address = "https://www.olx.bg/ad/prodavam-3-staen-apartamentna-ul-karlaka-2-CID368-ID86agm.html#1927cbea9d;promoted?page=22";

		try {
			URL url = new URL(address);

			System.err.println("Protocol: " + url.getProtocol());
			System.err.println("User info: " + url.getUserInfo());
			System.err.println("Host: " + url.getHost());
			System.err.println("Port: " + url.getPort());
			System.err.println("Path: " + url.getPath());
			System.err.println("Query: " + url.getQuery());
			System.err.println("Ref: " + url.getRef());

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@GetMapping("/jpa/criteria")
	@ResponseBody
	public String jpaCriteria() {

		CrawledSpecification spec = new CrawledSpecification();

		spec.add(new SearchCriteria("url", "mnogostaen", SearchOperation.MATCH));
		spec.add(new SearchCriteria("price", 90000, "crawledInfo", SearchOperation.LESS_THAN_EQUAL));

//		CrawledInfoSpecification spec2 = new CrawledInfoSpecification();
//		spec.add(new SearchCriteria("price", 90000, SearchOperation.LESS_THAN));
//		spec.add(new SearchCriteria("price", 50000, SearchOperation.GREATER_THAN));
//		spec.add(new SearchCriteria("size", 100, SearchOperation.GREATER_THAN));
//		spec.add(new SearchCriteria("description", "СОБСТВЕНИК", SearchOperation.MATCH));
//		spec.add(new SearchCriteria("crawled.url", "mnogostaen", SearchOperation.MATCH));

//		Page<Crawled> crawled = crawledService.findAll(
//				CrawledSpecification.getCrawledByName(new SearchCriteria("url", "mnogostaen", SearchOperation.MATCH)),
//				PageRequest.of(0, 20));

//		Page<Crawled> crawled = crawledService.findAll(CrawledSpecification.getCrawledByLessThenPrice(50000), PageRequest.of(0, 20));
//		Page<Crawled> crawled = crawledService.findAll(CrawledSpecification.getCrawledByGreaterByPrice(new SearchCriteria("price", 300000, SearchOperation.MATCH)), PageRequest.of(0, 20));

//		Page<Crawled> crawled = crawledService.findAll(
//				CrawledSpecification.getCrawledByPrice(new SearchCriteria("price", 50000, SearchOperation.MATCH),
//						new SearchCriteria("price", 55000, SearchOperation.MATCH)),
//				PageRequest.of(0, 20));

		Page<Crawled> crawled = crawledService.findAll(spec, PageRequest.of(0, 20));

		System.err.println(crawled);

		for (Crawled itr : crawled) {
			System.err.println(itr);
		}

		return crawled.toString();
	}

	@GetMapping("/jpa/sort")
	@ResponseBody
	public void jpaSort(@PageableDefault(size = 20, sort = "id", direction = Direction.DESC) Pageable pageable) {
		Page<Crawled> crawled = crawledService.findAll(pageable);

		System.err.println(crawled.getSort());

		String sort = crawled.getSort().stream()
				.map(order -> "&sort=" + order.getProperty() + "," + order.getDirection())
				.collect(Collectors.joining("&sort="));

		System.err.println(sort);
	}

	@GetMapping("/currency")
	@ResponseBody
	public void currency() {

		String str = "221000 лев. ЛЕВ.";
		String[] searchList = { "\u20AC", "EURO", "евро", "ЕВРО", "лев.", "ЛЕВ.", "ЛЕВА", "лева", "Лева" };
		String[] replacement = { "EUR", "EUR", "EUR", "EUR", "BGN", "BGN", "BGN", "BGN", "BGN" };

		str = str.replaceAll("[0-9\\s]", "").trim();
		str = StringUtils.replaceEach(str, searchList, replacement);

		System.err.println(str);
	}

	@GetMapping("/jpa/many-to-many")
	@ResponseBody
	public void jpaManyToMany() {

		User user = userService.findById(1);
		System.err.println(user);

		Crawled crawled = crawledService.findById(750);
		System.err.println(crawled);
		
		System.err.println(crawled.getUserCrawled());

		Set<UserCrawled> userCraweldSet = new HashSet<UserCrawled>();
		
		userCraweldSet.add(new UserCrawled(user, crawled, true, true, false));
		
		crawled.setUserCrawled(userCraweldSet);
		
		crawledService.save(crawled);

	}

}
