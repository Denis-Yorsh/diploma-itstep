package org.itstep.diploma.admin.controller;

import lombok.RequiredArgsConstructor;
import org.itstep.diploma.admin.service.AdminService;
import org.itstep.diploma.configs.security.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class UsersController {
	private final AdminService adminService;

	@PostMapping("/getAllUsers")
	public String getAllUser(Model model) {
		Optional<List<User>> allUsers = adminService.getAllUsers();
		allUsers.ifPresent(users -> model.addAttribute("allUsers", users));
		return "admin/getAllUsers";
	}
}
