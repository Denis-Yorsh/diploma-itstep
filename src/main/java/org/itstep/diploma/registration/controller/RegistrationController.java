package org.itstep.diploma.registration.controller;

import org.itstep.diploma.registration.validation.dto.UsersRegistrationDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class RegistrationController {

	@GetMapping("/registration")
	public String registrationGet() {
		return "registration";
	}


	@PostMapping("/registration")
	public String registrationPost(UsersRegistrationDto usersRegistrationDto) {
		System.err.println("\n\n\n-------------------------------------------------------------------------test\n\n\n");
		System.err.println("\n\n\n-------------------------------------------------------------------------test\n\n\n");
		System.out.println(usersRegistrationDto);
		System.err.println("\n\n\n-------------------------------------------------------------------------test\n\n\n");
		System.err.println("\n\n\n-------------------------------------------------------------------------test\n\n\n");
		System.err.println("\n\n\n-------------------------------------------------------------------------test\n\n\n");
		return "redirect:/";
	}
}