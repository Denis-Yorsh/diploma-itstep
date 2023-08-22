package org.itstep.diploma.info.controllers;

import lombok.RequiredArgsConstructor;
import org.itstep.diploma.info.entity.ContactMessage;
import org.itstep.diploma.info.service.ContactService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/info/")
@RequiredArgsConstructor
public class InfoController {
	private final ContactService contactService;

	@GetMapping("/aboutus")
	public String aboutUs() {
		return "site/aboutUsPage";
	}

	@GetMapping("/contacts")
	public String contacts() {
		return "site/contactsPage";
	}

	@GetMapping("/massage/{name}/{email}/{massage}")
	public String massage(@PathVariable("name") String name,
						  @PathVariable("email") String email,
						  @PathVariable("massage") String massage,
						  ContactMessage contactMessage) {
		contactService.addContactMessage(contactMessage, name, email, massage);
		return "redirect:/";
	}
}
