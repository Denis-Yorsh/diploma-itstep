package org.itstep.diploma.admin.service;

import lombok.RequiredArgsConstructor;
import org.itstep.diploma.admin.dto.AddDeleteRoleDto;
import org.itstep.diploma.configs.security.entity.Role;
import org.itstep.diploma.configs.security.entity.User;
import org.itstep.diploma.configs.security.repository.UserRepository;
import org.itstep.diploma.contact.message.entity.ContactMessage;
import org.itstep.diploma.contact.message.repository.ContactMessageRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminServiceImp implements AdminService, AddDeleteRoleService {
	private final UserRepository userRepository;
	private final ContactMessageRepository contactMessageRepository;

	@Transactional(readOnly = true)
	@Override
	public Optional<List<ContactMessage>> getAllContactMessage() {
		List<ContactMessage> allMessages = contactMessageRepository.findAll();
		if (allMessages.isEmpty()) {
			return Optional.empty();
		}
		return Optional.of(allMessages);
	}

	@Transactional(readOnly = true)
	@Override
	public Optional<List<User>> getAllUsers() {
		List<User> allUsers = userRepository.findAll();
		if (allUsers.isEmpty()) {
			return Optional.empty();
		}
		return Optional.of(allUsers);
	}

	@Transactional
	@Override
	public String addRole(AddDeleteRoleDto addDeleteRoleDto, Role role) {
		String response;
		role.setAuthority(addDeleteRoleDto.getRole());
		try {
			Optional<User> optionalUser = userRepository.findByUsername(addDeleteRoleDto.getUsername());
			if (optionalUser.isPresent()) {
				User userByUsername = optionalUser.get();
				boolean isExist = userByUsername
						.getAuthorities()
						.stream()
						.anyMatch(roles -> roles.getAuthority().equals(addDeleteRoleDto.getRole()));
				if (isExist) {
					return "role %s is already exist in this user".formatted(addDeleteRoleDto.getRole());
				}
				userByUsername.addRole(role);
				return "role is added";
			}
			response = ("role is not added, " +
						"user %s does not exist").formatted(addDeleteRoleDto.getUsername());
		} catch (Exception e) {
			response = e.getMessage();
		}
		return response;
	}

	@Transactional
	@Override
	public String deleteRole(AddDeleteRoleDto addDeleteRoleDto, Role role) {
		String response;
		role.setAuthority(addDeleteRoleDto.getRole());
		try {
			Optional<User> optionalUser = userRepository.findByUsername(addDeleteRoleDto.getUsername());
			if (optionalUser.isPresent()) {
				User userByUsername = optionalUser.get();
				boolean isExist = userByUsername
						.getAuthorities()
						.stream()
						.anyMatch(roles -> roles.getAuthority().equals(addDeleteRoleDto.getRole()));
				if (!isExist) {
					return "role %s no exist in this user".formatted(addDeleteRoleDto.getRole());
				}
				userByUsername.removeRole(role);
				return "role is deleted";
			}
			response = ("role is not deleted, " +
						"user %s does not exist").formatted(addDeleteRoleDto.getUsername());
		} catch (Exception e) {
			response = e.getMessage();
		}
		return response;
	}
}