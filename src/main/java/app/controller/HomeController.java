package app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import app.service.CrawledService;

@Controller
public class HomeController {

	@Autowired
	private CrawledService crawledService;

	@GetMapping
	public String index(Model model) {

		model.addAttribute("crawled", crawledService.findAll(PageRequest.of(0, 10, Sort.by("id").descending())));

		return "home/index";
	}

	@GetMapping("/view/{id}")
	public String view(Model model, @PathVariable("id") int id) {

		model.addAttribute("crawled", crawledService.findById(id));

		return "home/view";
	}

}
