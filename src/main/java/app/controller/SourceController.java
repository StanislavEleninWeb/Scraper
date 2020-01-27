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

import app.entity.Source;
import app.service.SourceService;

@Controller
@RequestMapping("/source")
public class SourceController {

	@Autowired
	private SourceService sourceService;

	@GetMapping
	public String index(Model model) {
		model.addAttribute("sources", sourceService.findAll());
		return "source/index";
	}

	@GetMapping("/add")
	public String add(@ModelAttribute("source") Source source) {
		return "source/add";
	}

	@GetMapping("/{id}/edit")
	public String edit(@PathVariable("id") int id, Model model) {
		model.addAttribute("source", sourceService.findById(id));
		return "source/add";
	}

	@PostMapping("/save")
	public String save(@Valid @ModelAttribute("source") Source source, BindingResult bindingResult) {

		if (bindingResult.hasErrors())
			return "source/add";

		Source result = sourceService.save(source);

		return "redirect:/source/" + result.getId() + "/edit";
	}

	@GetMapping("/{id}/delete")
	public String delete(@PathVariable("id") int id) {
		sourceService.deleteById(id);
		return "redirect:/source";
	}

}
