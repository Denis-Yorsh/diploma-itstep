package org.itstep.diploma.registration.servise;

import lombok.RequiredArgsConstructor;
import org.itstep.diploma.configs.security.entity.Role;
import org.itstep.diploma.configs.security.entity.User;
import org.itstep.diploma.configs.security.repository.UserRepository;
import org.itstep.diploma.registration.dto.UserRegistrationDto;
import org.itstep.diploma.registration.entity.UserRegistration;
import org.itstep.diploma.registration.repository.UserRegistrationRepository;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserRegistrationServiceImp implements UserRegistrationService {
	private final UserRepository userRepository;
	private final UserRegistrationRepository userRegistrationRepository;
	private final PasswordEncoder passwordEncoder;

	@Transactional
	@Override
	public void createUser(UserRegistrationDto userRegistrationDto,
						   User user,
						   Role role,
						   UserRegistration userRegistration) {
		user.setUsername(userRegistrationDto.getUsername());
		user.setPassword(passwordEncoder.encode(userRegistrationDto.getPassword()));
		role.setAuthority("ROLE_USER");
		userRegistration.setFirstName(userRegistrationDto.getFirstName());
		userRegistration.setLastName(userRegistrationDto.getLastName());
		userRegistration.setDayBirthday(userRegistrationDto.getDayBirthday());
		userRegistration.setEmail(userRegistrationDto.getEmail());
		userRegistration.setDayRegistration(LocalDateTime.now());

		userRepository.save(user);
		user.addRole(role);
		user.addUsersRegistration(userRegistration);
	}

	@Transactional(readOnly = true)
	@Override
	public Map<String, Boolean> checkUserNameAndEmail(String username, String email) {
		Map<String, Boolean> checked = new HashMap<>();
		checked.put("username", false);
		checked.put("email", false);
		Optional<User> byUsername = userRepository.findByUsername(username);
		Optional<UserRegistration> byEmail = userRegistrationRepository.findByEmail(email);
		if (byUsername.isPresent()) {
			checked.put("username", true);
		}
		if (byEmail.isPresent()) {
			checked.put("email", true);
		}
		return checked;
	}
}
