package app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import app.entity.BuildType;
import app.service.BuildTypeService;

@Controller
@RequestMapping("/build/type")
public class BuildTypeController {

	@Autowired
	private BuildTypeService buildTypeService;

	@GetMapping
	public String index(Model model) {
		model.addAttribute("buildTypes", buildTypeService.findAll());
		return "buildType/index";
	}

	@GetMapping("/add")
	public String add(@ModelAttribute("buildType") BuildType buildType) {
		return "buildType/add";
	}

	@GetMapping("/{id}/edit")
	public String edit(@PathVariable("id") int id, Model model) {
		model.addAttribute("buildType", buildTypeService.findById(id));
		return "buildType/add";
	}

	@PostMapping("/save")
	public String save(@ModelAttribute("buildType") BuildType buildType, BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			return "buildType/add";

		BuildType result = buildTypeService.save(buildType);

		return "redirect:/build/type/" + result.getId() + "/edit";
	}

}
