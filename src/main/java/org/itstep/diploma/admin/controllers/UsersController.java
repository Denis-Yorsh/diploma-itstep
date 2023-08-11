package org.itstep.diploma.admin.controllers;

import lombok.RequiredArgsConstructor;
import org.apache.tomcat.websocket.WsWebSocketContainer;
import org.itstep.diploma.admin.service.AdminService;
import org.itstep.diploma.configs.security.entity.Users;
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
		Optional<List<Users>> allUsers = adminService.getAllUsers();
		allUsers.ifPresent(users -> model.addAttribute("allUsers", users));
		return "admin/getAllUsers";
	}
}