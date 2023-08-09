package org.itstep.diploma.info.service;

import org.itstep.diploma.info.entity.ContactMessage;

public interface ContactService {
	void addContactMessage(ContactMessage contactMessage, String name, String email, String message);
	void deleteContactMessage(Long id);
}
