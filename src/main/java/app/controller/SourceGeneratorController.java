package app.controller;

import java.util.Optional;

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
import org.springframework.web.bind.annotation.RequestParam;

import app.entity.SourceGenerator;
import app.service.SourceGeneratorService;
import app.service.SourceService;

@Controller
@RequestMapping("/source/generator")
public class SourceGeneratorController {

	@Autowired
	private SourceService sourceService;

	@Autowired
	private SourceGeneratorService sourceGeneratorService;

	@GetMapping("/add")
	public String add(@ModelAttribute("sourceGenerator") SourceGenerator sourceGenerator,
			@RequestParam("source") int source) {

		sourceGenerator.setId(source);

		return "source/generator/add";
	}

	@GetMapping("/{id}/edit")
	public String edit(@PathVariable("id") int id, Model model) {

		Optional<SourceGenerator> sourceGenerator = sourceGeneratorService.findById(id);

		if (!sourceGenerator.isPresent())
			return "redirect:/source/generator/add?source=" + id;

		model.addAttribute("sourceGenerator", sourceGenerator.get());

		return "source/generator/add";
	}

	@PostMapping("/save")
	public String save(@Valid @ModelAttribute("sourceGenerator") SourceGenerator sourceGenerator,
			BindingResult bindingResult) {

		if (!sourceService.existsById(sourceGenerator.getId()))
			return "redirect:/source";

		if (bindingResult.hasErrors())
			return "source/generator/add";

		sourceGeneratorService.save(sourceGenerator);

		return "redirect:/source/generator/" + sourceGenerator.getId() + "/edit";
	}

}
