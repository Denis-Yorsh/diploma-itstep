package org.itstep.diploma.admin.service;

import org.itstep.diploma.configs.security.entity.Users;
import org.itstep.diploma.info.entity.ContactMessage;

import java.util.List;
import java.util.Optional;

public interface AdminService {
	Optional<List<ContactMessage>> getAll();
	Optional<List<Users>> getAllUsers();
}
