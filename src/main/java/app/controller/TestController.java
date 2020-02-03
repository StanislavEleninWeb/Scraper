package app.controller;

import java.util.HashMap;

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

		String link = "https://www.alo.bg/5528646";

		Source source = sourceService.getOne(1);

		// Scrape content
		ContentScraper contentScraper = new ContentScraper(link);
		if (contentScraper.getPage() == null) {
			System.out.println("Failed to fetch page " + link);
		}

		AnalyzeContent analyzeContent = null;
		try {
			analyzeContent = (AnalyzeContent) Class.forName(source.getSourceGenerator().getContentRegex())
					.newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
		}

		analyzeContent.setPage(contentScraper.getPage());
		analyzeContent.analyze();

		System.err.println(analyzeContent);

		return "";
	}

}
