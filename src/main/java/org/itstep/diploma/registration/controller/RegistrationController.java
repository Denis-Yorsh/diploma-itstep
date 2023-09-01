package org.itstep.diploma.registration.controller;

import lombok.RequiredArgsConstructor;
import org.itstep.diploma.registration.dto.UserRegistrationDto;
import org.itstep.diploma.registration.servise.UserRegistrationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

@Controller
@RequiredArgsConstructor
public class RegistrationController {
	private final UserRegistrationService userRegistrationService;

	@GetMapping("/registration")
	public String registrationGet(@ModelAttribute("usersRegistration") UserRegistrationDto userRegistrationDto) {
		return "registration";
	}

	@PostMapping("/registration")
	public String registrationPost(@ModelAttribute("usersRegistration")
								   @Validated UserRegistrationDto userRegistrationDto,
								   BindingResult bindingResult,
								   Model model) {
		if (bindingResult.hasErrors()) {
			checkUserNameAndEmail(userRegistrationDto, model);
			checkPasswords(userRegistrationDto, model);
			return "registration";
		}
		if (checkUserNameAndEmail(userRegistrationDto, model)) {
			checkPasswords(userRegistrationDto, model);
			return "registration";
		}
		if (checkPasswords(userRegistrationDto, model)) {
			return "registration";
		}
		userRegistrationService.createUser(userRegistrationDto);
		return "redirect:login";
	}

	private boolean checkUserNameAndEmail(UserRegistrationDto userRegistrationDto, Model model) {
		Map<String, Boolean> checked = userRegistrationService.checkUserNameAndEmail(
				userRegistrationDto.getUsername(), userRegistrationDto.getEmail());
		if (checked.get("username")) {
			if (checked.get("email")) {
				model.addAttribute("email", "email %s already exists"
						.formatted(userRegistrationDto.getEmail()));
			}
			model.addAttribute("username", "username %s already exists"
					.formatted(userRegistrationDto.getUsername()));
			return true;
		}
		if (checked.get("email")) {
			model.addAttribute("email", "email %s already exists"
					.formatted(userRegistrationDto.getEmail()));
			return true;
		}
		return false;
	}

	private static boolean checkPasswords(UserRegistrationDto userRegistrationDto, Model model) {
		if (!userRegistrationDto.getPassword().equals(userRegistrationDto.getRepeatPassword())) {
			model.addAttribute("password", "password mismatch");
			return true;
		}
		return false;
	}
}