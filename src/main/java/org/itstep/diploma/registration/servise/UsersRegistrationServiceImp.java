package org.itstep.diploma.registration.servise;

import lombok.RequiredArgsConstructor;
import org.itstep.diploma.configs.security.entity.Roles;
import org.itstep.diploma.configs.security.entity.Users;
import org.itstep.diploma.configs.security.repository.UserRepository;
import org.itstep.diploma.registration.dto.UsersRegistrationDto;
import org.itstep.diploma.registration.entity.UsersRegistration;
import org.itstep.diploma.registration.repository.UsersRegistrationRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsersRegistrationServiceImp implements UsersRegistrationService {
	private final UserRepository userRepository;
	private final UsersRegistrationRepository usersRegistrationRepository;
	private final PasswordEncoder passwordEncoder;

	@Transactional
	@Override
	public void createUser(Users users,
						   Roles roles,
						   UsersRegistration usersRegistration,
						   UsersRegistrationDto usersRegistrationDto) {
		users.setUsername(usersRegistrationDto.getUsername());
		users.setPassword(passwordEncoder.encode(usersRegistrationDto.getPassword()));
		roles.setAuthority("ROLE_USER");
		usersRegistration.setFirstName(usersRegistrationDto.getFirstName());
		usersRegistration.setLastName(usersRegistrationDto.getLastName());
		usersRegistration.setDayBirthday(usersRegistrationDto.getDayBirthday());
		usersRegistration.setEmail(usersRegistrationDto.getEmail());
		usersRegistration.setDayRegistration(LocalDateTime.now());

		userRepository.save(users);
		users.addRole(roles);
		users.addUsersRegistration(usersRegistration);
	}

	@Transactional(readOnly = true)
	@Override
	public Map<String, Boolean> checkUserNameAndEmail(String username, String email) {
		Map<String, Boolean> checked = new HashMap<>();
		checked.put("username", false);
		checked.put("email", false);
		Optional<Users> byUsername = userRepository.findByUsername(username);
		Optional<UsersRegistration> byEmail = usersRegistrationRepository.findByEmail(email);
		if (byUsername.isPresent()) {
			checked.put("username", true);
		}
		if (byEmail.isPresent()) {
			checked.put("email", true);
		}
		return checked;
	}
}
