package org.itstep.diploma.registration.controller;

import lombok.RequiredArgsConstructor;
import org.itstep.diploma.registration.servise.UsersRegistrationService;
import org.itstep.diploma.registration.validation.dto.UsersRegistrationDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class RegistrationController {
	private final UsersRegistrationService usersRegistrationService;

	@GetMapping("/registration")
	public String registrationGet() {
		return "registration";
	}


	@PostMapping("/registration")
	public String registrationPost(UsersRegistrationDto usersRegistrationDto) {
		usersRegistrationService.createUser(usersRegistrationDto); // test
		return "redirect:login";
	}
}