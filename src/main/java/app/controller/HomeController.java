package app.controller;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import app.entity.Crawled;
import app.entity.UserCriteria;
import app.repository.specs.CrawledSpecification;
import app.service.BuildTypeService;
import app.service.CrawledService;
import app.service.ResidenceTypeService;
import app.service.UserCriteriaService;
import app.service.UserService;

@Controller
public class HomeController {

	@Autowired
	private UserService userService;

	@Autowired
	private UserCriteriaService userCriteriaService;

	@Autowired
	private CrawledService crawledService;

	@Autowired
	private ResidenceTypeService residenceTypeService;

	@Autowired
	private BuildTypeService buildTypeService;

	@GetMapping
	public String index(@PageableDefault(size = 20, sort = "id", direction = Direction.DESC) Pageable pageable,
			Model model) {

		model.addAttribute("residenceType", residenceTypeService.findAll());
		model.addAttribute("buildType", buildTypeService.findAll());

		UserCriteria criteria = userCriteriaService.findByUserAndPrimary(userService.findById(1), true);
		model.addAttribute("userCriteria", criteria);

		Page<Crawled> crawled = crawledService.findAll(
				CrawledSpecification.getCrawledBySearchCriteria(criteria.getKeywords(), criteria.getPriceMin(),
						criteria.getPriceMax(), criteria.getPricePerSquareMin(), criteria.getPricePerSquareMax(),
						criteria.getSizeMin(), criteria.getSizeMax(), criteria.getType(), criteria.getBuildType()),
				pageable);
		model.addAttribute("crawled", crawled);

		String sort = crawled.getSort().stream().map(order -> order.getProperty() + "," + order.getDirection())
				.collect(Collectors.joining("&sort="));
		model.addAttribute("sort", sort);

		return "home/index";
	}

	@GetMapping("/view/{id}")
	public String view(Model model, @PathVariable("id") int id) {

		model.addAttribute("crawled", crawledService.findById(id));

		return "home/view";
	}

}
