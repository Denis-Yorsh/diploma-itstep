package org.itstep.diploma.admin.controller;

import lombok.RequiredArgsConstructor;
import org.itstep.diploma.admin.service.AdminService;
import org.itstep.diploma.info.dto.ContactMessageDto;
import org.itstep.diploma.info.entity.ContactMessage;
import org.itstep.diploma.info.service.ContactService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class ContactMessageController {
	private final AdminService adminService;
	private final ContactService contactService;

	@PostMapping("/getAllContactMessages")
	public String getAll(Model model) {
		Optional<List<ContactMessage>> allMessages = adminService.getAll();
		allMessages.ifPresent(contactMessages -> model.addAttribute("allContactMessages", contactMessages));
		return "admin/getAllContactMessages";
	}

	@DeleteMapping("/delete")
	public String deleteMessage(ContactMessageDto contactMessageDto, Model model) {
		contactService.deleteContactMessage(contactMessageDto.getId());
		Optional<List<ContactMessage>> allMessages = adminService.getAll();
		allMessages.ifPresent(contactMessages -> model.addAttribute("allContactMessages", contactMessages));
		return "admin/getAllContactMessages";
	}
}
