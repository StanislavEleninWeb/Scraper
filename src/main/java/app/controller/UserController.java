package app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import app.entity.User;

@Controller
public class UserController {

	@GetMapping("/login")
	public String login() {
		return "user/login";
	}

	@GetMapping("/register")
	public String register(@ModelAttribute("user") User user) {
		return "user/register";
	}

	@PostMapping("/register")
	public String register(@ModelAttribute("user") User user, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return "user/register";
		}

		return "redirect:/welcome";
	}

	@GetMapping("/forgotten-password")
	public String forgottenPassword() {
		return "user/forgotten-password";
	}

	@GetMapping("/activation-code/{activation-code}")
	public String activationCode() {
		return "redirect:/login?message=Success";
	}

	@GetMapping("/welcome")
	public String welcome() {
		return "user/welcome";
	}

}
