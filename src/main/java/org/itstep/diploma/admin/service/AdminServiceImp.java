package org.itstep.diploma.admin.service;

import lombok.RequiredArgsConstructor;
import org.itstep.diploma.configs.security.entity.Users;
import org.itstep.diploma.configs.security.repository.UserRepository;
import org.itstep.diploma.info.entity.ContactMessage;
import org.itstep.diploma.info.repository.ContactMessageRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminServiceImp implements AdminService {
	private final UserRepository userRepository;
	private final ContactMessageRepository contactMessageRepository;

	@Transactional(readOnly = true)
	@Override
	public Optional<List<ContactMessage>> getAll() {
		List<ContactMessage> allMessages = contactMessageRepository.findAll();
		if (allMessages.isEmpty()) {
			return Optional.empty();
		}
		return Optional.of(allMessages);
	}

	@Transactional(readOnly = true)
	@Override
	public Optional<List<Users>> getAllUsers() {
		List<Users> allUsers = userRepository.findAll();
		if (allUsers.isEmpty()) {
			return Optional.empty();
		}
		return Optional.of(allUsers);
	}
}
