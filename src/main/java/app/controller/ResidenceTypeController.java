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

import app.entity.ResidenceType;
import app.service.ResidenceTypeService;

@Controller
@RequestMapping("/residence/type")
public class ResidenceTypeController {

	@Autowired
	private ResidenceTypeService residenceTypeService;

	@GetMapping
	public String index(Model model) {
		model.addAttribute("residenceTypes", residenceTypeService.findAll());
		return "residenceType/index";
	}

	@GetMapping("/add")
	public String add(@ModelAttribute("residenceType") ResidenceType residenceType) {
		return "residenceType/add";
	}

	@GetMapping("/{id}/edit")
	public String edit(@PathVariable("id") int id, Model model) {
		model.addAttribute("residenceType", residenceTypeService.findById(id));
		return "residenceType/add";
	}

	@PostMapping("/save")
	public String save(@ModelAttribute("residenceType") ResidenceType residenceType, BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			return "residenceType/add";

		ResidenceType result = residenceTypeService.save(residenceType);

		return "redirect:/residence/type/" + result.getId() + "/edit";
	}

}
