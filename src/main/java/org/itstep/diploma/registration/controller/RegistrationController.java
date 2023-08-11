package org.itstep.diploma.registration.controller;

import lombok.RequiredArgsConstructor;
import org.itstep.diploma.configs.security.entity.Roles;
import org.itstep.diploma.configs.security.entity.Users;
import org.itstep.diploma.registration.dto.UsersRegistrationDto;
import org.itstep.diploma.registration.entity.UsersRegistration;
import org.itstep.diploma.registration.servise.UsersRegistrationService;
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
	private final UsersRegistrationService usersRegistrationService;

	@GetMapping("/registration")
	public String registrationGet(@ModelAttribute("usersRegistration") UsersRegistrationDto usersRegistrationDto) {
		return "registration";
	}

	@PostMapping("/registration")
	public String registrationPost(@ModelAttribute("usersRegistration")
								   @Validated UsersRegistrationDto usersRegistrationDto,
								   BindingResult bindingResult,
								   Model model) {
		if (bindingResult.hasErrors()) {
			checkUserNameAndEmail(usersRegistrationDto, model);
			checkPasswords(usersRegistrationDto, model);
			return "registration";
		}
		if (checkUserNameAndEmail(usersRegistrationDto, model)) {
			checkPasswords(usersRegistrationDto, model);
			return "registration";
		}
		if (checkPasswords(usersRegistrationDto, model)) {
			return "registration";
		}
		usersRegistrationService.createUser(new Users(), new Roles(), new UsersRegistration(), usersRegistrationDto);
		return "redirect:login";
	}

	private boolean checkUserNameAndEmail(UsersRegistrationDto usersRegistrationDto, Model model) {
		Map<String, Boolean> checked = usersRegistrationService.checkUserNameAndEmail(
				usersRegistrationDto.getUsername(), usersRegistrationDto.getEmail());
		if (checked.get("username")) {
			if (checked.get("email")) {
				model.addAttribute("email", "email %s already exists"
						.formatted(usersRegistrationDto.getEmail()));
			}
			model.addAttribute("username", "username %s already exists"
					.formatted(usersRegistrationDto.getUsername()));
			return true;
		}
		if (checked.get("email")) {
			model.addAttribute("email", "email %s already exists"
					.formatted(usersRegistrationDto.getEmail()));
			return true;
		}
		return false;
	}

	private static boolean checkPasswords(UsersRegistrationDto usersRegistrationDto, Model model) {
		if (!usersRegistrationDto.getPassword().equals(usersRegistrationDto.getRepeatPassword())) {
			model.addAttribute("password", "password mismatch");
			return true;
		}
		return false;
	}
}