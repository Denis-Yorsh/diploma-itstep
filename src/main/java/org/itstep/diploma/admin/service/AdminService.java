package org.itstep.diploma.admin.service;

import org.itstep.diploma.configs.security.entity.User;
import org.itstep.diploma.contact.message.entity.ContactMessage;

import java.util.List;
import java.util.Optional;

public interface AdminService {
	Optional<List<ContactMessage>> getAllContactMessage();

	Optional<List<User>> getAllUsers();
}
