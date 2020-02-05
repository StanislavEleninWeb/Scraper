package app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ScraperRestController {

	@GetMapping("/scraper")
	public String scraper() {

//		LinksScraper scraper = new LinksScraper();

//		Alo.bg
//		scraper.setUrl("https://www.alo.bg/obiavi/imoti-prodajbi/apartamenti-stai/?region_id=16");
//		scraper.setRegex("//div[@id=\"content_container\"]//a/@href");

//		Olx.bg
//		scraper.setUrl("https://www.olx.bg/nedvizhimi-imoti/prodazhbi/apartamenti/plovdiv/?search%5Bdescription%5D=1");
//		scraper.setRegex("//div[contains(@class,'rel listHandler')]//a/@href");

//		Bulgarian properties
//		scraper.setUrl("https://www.bulgarianproperties.bg/Search/index.php?minarea=&maxprice=&skeyword=&sadx=1&sady=&spredlog=innear&scntr=1&c=%D2%DA%D0%D1%C8&stown=2833");
//		scraper.setRegex("//div[@class=\"items\"]//a/@href");

//		System.out.println(scraper.getLinks());

		return "Hello";
	}

}
