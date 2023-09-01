package org.itstep.diploma.contact.message.service;

import lombok.RequiredArgsConstructor;
import org.itstep.diploma.contact.message.entity.ContactMessage;
import org.itstep.diploma.contact.message.repository.ContactMessageRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ContactServiceImp implements ContactService {
	private final ContactMessageRepository contactMessageRepository;

	@Transactional
	@Override
	public void addContactMessage(ContactMessage contactMessage, String name, String email, String message) {
		contactMessage.setName(name);
		contactMessage.setEmail(email);
		contactMessage.setMassage(message);
		contactMessageRepository.save(contactMessage);
	}

	@Transactional
	@Override
	public void deleteContactMessage(Long id) {
		Optional<ContactMessage> byId = contactMessageRepository.findById(id);
		byId.ifPresent(contactMessageRepository::delete);
	}
}
