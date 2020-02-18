package app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import app.service.CrawledService;

@Controller
public class HomeController {
	
	@Autowired
	private CrawledService crawledService;

	@GetMapping
	public String index(Model model) {
		
		model.addAttribute("crawled", crawledService.findAll());
		
		return "home/index";
	}

}
