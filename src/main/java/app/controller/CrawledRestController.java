package app.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.entity.Crawled;
import app.entity.User;
import app.service.CrawledService;

@RestController
@RequestMapping("/crawled")
public class CrawledRestController {

	private CrawledService crawledService;

	@PostMapping("/markCrawledUrlAsViewedByUser")
	public Crawled markCrawledUrlAsViewedByUser(@PathVariable("crawled") Crawled crawled, @PathVariable("user") User user) {
		
		
		
		return crawledService.save(crawled);
	}

}
