package org.itstep.diploma.info.service;

import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.itstep.diploma.info.entity.ContactMessage;
import org.itstep.diploma.info.repository.ContactMessageRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ContactService {
	private final ContactMessageRepository contactMessageRepository;
	private final ContactMessage contactMessage;

	@Transactional
	public void addContactMessage(String name, String email, String message) {
		try {
			contactMessage.setName(name);
			contactMessage.setEmail(email);
			contactMessage.setMassage(message);
			contactMessageRepository.save(contactMessage);
		} catch (ValidationException e) {
			System.err.println(e.getMessage());
		}
	}
}
