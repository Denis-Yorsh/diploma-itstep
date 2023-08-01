package org.itstep.diploma.registration.servise;

import lombok.RequiredArgsConstructor;
import org.itstep.diploma.configs.security.entity.Roles;
import org.itstep.diploma.configs.security.entity.Users;
import org.itstep.diploma.configs.security.repository.UserRepository;
import org.itstep.diploma.registration.entity.UsersRegistration;
import org.itstep.diploma.registration.validation.dto.UsersRegistrationDto;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class UsersRegistrationServiceImp implements UsersRegistrationService{
	private final UserRepository userRepository;
	private final Users users;
	private final Roles roles;
	private final UsersRegistration usersRegistration;
	private final PasswordEncoder passwordEncoder;

	@Transactional
	@Override
	public Users createUser(UsersRegistrationDto usersRegistrationDto) {
		users.setUsername(usersRegistrationDto.getUsername());
		users.setPassword(passwordEncoder.encode(usersRegistrationDto.getPassword()));
		roles.setAuthority("ROLE_USER");
		usersRegistration.setFirstName(usersRegistrationDto.getFirstName());
		usersRegistration.setLastName(usersRegistrationDto.getLastName());
		usersRegistration.setDayBirthday(usersRegistrationDto.getDayBirthday());
		usersRegistration.setEmail(usersRegistrationDto.getEmail());
		usersRegistration.setDayRegistration(LocalDate.now());

		userRepository.save(users);
		users.addRole(roles);
		users.addUsersRegistration(usersRegistration);
		return users;
	}
}
