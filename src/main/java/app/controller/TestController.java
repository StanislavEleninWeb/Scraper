package app.controller;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import app.entity.Source;
import app.scraper.ContentScraper;
import app.scraper.analyze.AnalyzeContent;
import app.service.SourceService;

@Controller
@RequestMapping("test")
public class TestController {

	@Autowired
	private SourceService sourceService;

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

		String link = "https://www.olx.bg/ad/tristaen-v-kyuchuk-parizh-CID368-ID7khdh.html#217d944456";

		Source source = sourceService.getOne(2);

		// Scrape content
		ContentScraper contentScraper = null;
		try {
			contentScraper = new ContentScraper(link);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (contentScraper.getPage() == null) {
			System.out.println("Failed to fetch page " + link);
		}

		AnalyzeContent analyzeContent = null;
		try {
			analyzeContent = (AnalyzeContent) Class.forName(source.getSourceGenerator().getContentAnalyzer())
					.newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
		}

		analyzeContent.setHtml(contentScraper.getPage());
		try {
			analyzeContent.analyze();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.err.println(analyzeContent);

		return analyzeContent.toString();
	}

	@GetMapping("/regex")
	@ResponseBody
	public String regex() {

		String str = "57 948 EUR597.40 EUR/кв.м";
		String regex = "\\d+(?:[\\.\\s]\\d+)?";

		Pattern pattern = Pattern.compile(regex, Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE | Pattern.MULTILINE);

		System.err.println(pattern.pattern());
		
		str = str.replaceAll("&"+"nbsp;", " "); 
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

}
