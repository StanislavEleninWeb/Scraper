package app.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import app.entity.Crawled;
import app.entity.User;
import app.entity.UserCrawled;
import app.service.CrawledService;
import app.service.UserCrawledService;

@RestController
@RequestMapping("/rest/crawled")
public class CrawledRestController {

	@Autowired
	private CrawledService crawledService;

	@Autowired
	private UserCrawledService userCrawledService;

	@GetMapping("/markCrawledUrlAsViewedByUser")
	@ResponseStatus(code = HttpStatus.OK)
	public void markCrawledUrlAsViewedByUser(@RequestParam Crawled crawled, @RequestParam User user) {

		UserCrawled userCrawled = userCrawledService.findByUserAndCrawled(user, crawled);
		if (userCrawled == null)
			userCrawled = new UserCrawled(user, crawled, true, false, false);
		else
			userCrawled.setViewed(true);

		Set<UserCrawled> userCrawledSet = crawled.getUserCrawled();
		userCrawledSet.add(userCrawled);

		crawled.setUserCrawled(userCrawledSet);

		crawledService.save(crawled);
	}

}
