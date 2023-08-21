package org.itstep.diploma.admin.controllers;

import lombok.RequiredArgsConstructor;
import org.itstep.diploma.admin.dto.AddDeleteRoleDto;
import org.itstep.diploma.admin.service.AddDeleteRoleService;
import org.itstep.diploma.configs.security.entity.Roles;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Objects;

@Controller
@RequiredArgsConstructor
@RequestMapping("/homeRole")
public class AddDeleteRoleController {
	private final AddDeleteRoleService addDeleteRoleService;

	@PostMapping("/")
	public String homeRole() {
		return "admin/addDeleteRole";
	}

	@ResponseBody
	@PostMapping("/addRole")
	public String addRole(@Validated AddDeleteRoleDto addDeleteRoleDto, BindingResult bindingResult, Roles role) {
		if (bindingResult.hasErrors()) {
			return "add---" + Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage();
		}
		return addDeleteRoleDto.getRole().equals("noChoice")
				? "add---role no choice"
				: addDeleteRoleService.addRole(addDeleteRoleDto, role);
	}

	@ResponseBody
	@DeleteMapping("/deleteRole")
	public String deleteRole(@Validated AddDeleteRoleDto addDeleteRoleDto, BindingResult bindingResult, Roles role) {
		if (bindingResult.hasErrors()) {
			return "delete---" + Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage();
		}
		return addDeleteRoleDto.getRole().equals("noChoice")
				? "delete---role no choice"
				: addDeleteRoleService.deleteRole(addDeleteRoleDto, role);
	}
}
