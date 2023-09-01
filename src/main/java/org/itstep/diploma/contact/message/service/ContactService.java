package org.itstep.diploma.contact.message.service;

import org.itstep.diploma.contact.message.entity.ContactMessage;

public interface ContactService {
	void addContactMessage(ContactMessage contactMessage, String name, String email, String message);

	void deleteContactMessage(Long id);
}
