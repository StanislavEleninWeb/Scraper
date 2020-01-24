package app.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import app.entity.SourceGenerator;
import app.service.SourceGeneratorService;

@Controller
@RequestMapping("/source/generator/{source}")
public class SourceGeneratorController {

	@Autowired
	private SourceGeneratorService sourceGeneratorService;

	@GetMapping("/add")
	public String add(@PathVariable("source") int source, Model model) {
		model.addAttribute("source", source);
		return "source/generator/add";
	}

	@GetMapping("/edit")
	public String edit(@PathVariable("source") int source, Model model) {
		model.addAttribute("sourceGenerator", sourceGeneratorService.findById(source));
		return "source/generator/add";
	}

	@PostMapping("/save")
	public String save(@Valid @ModelAttribute("source") SourceGenerator sourceGenerator, BindingResult bindingResult) {
		return "redirect:/source";
	}

	@GetMapping("/{id}/delete")
	public String delete(@PathVariable("id") int id) {
		sourceGeneratorService.deleteById(id);
		return "redirect:/source";
	}

}
