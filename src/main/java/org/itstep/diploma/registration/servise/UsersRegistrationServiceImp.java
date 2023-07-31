package org.itstep.diploma.registration.servise;

import lombok.RequiredArgsConstructor;
import org.itstep.diploma.configs.security.entity.Roles;
import org.itstep.diploma.configs.security.entity.Users;
import org.itstep.diploma.configs.security.repository.UserRepository;
import org.itstep.diploma.registration.validation.dto.UsersRegistrationDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UsersRegistrationServiceImp implements UsersRegistrationService{
	private final UserRepository userRepository;
	private final Users users;
	private final Roles roles;

	@Transactional
	@Override
	public Users createUser(UsersRegistrationDto usersRegistrationDto) {
		users.setUsername(usersRegistrationDto.getUsername());
		users.setPassword(usersRegistrationDto.getPassword());
		roles.setAuthority("ROLE_USER");
		userRepository.save(users);
		users.addRole(roles);
		return users;
	}
}
