package org.itstep.diploma.configs.security.init;

import lombok.RequiredArgsConstructor;
import org.itstep.diploma.configs.security.entity.Roles;
import org.itstep.diploma.configs.security.entity.Users;
import org.itstep.diploma.configs.security.repository.UserRepository;
import org.itstep.diploma.registration.entity.UsersRegistration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
@RequiredArgsConstructor
@Profile("production")
public class InitDatabase implements CommandLineRunner {
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final Users admin;
	private final Roles roleAdmin;
	private final Roles roleWriter;
	private final Roles roleUser;
	private final UsersRegistration usersRegistration;
	@Value("${spring.security.admin.password:admin}")
	private String password;
	@Value("${spring.security.admin.email:default_admin@email.com}")
	private String email;

	@Transactional
	@Override
	public void run(String... args) throws Exception {
		roleAdmin.setAuthority("ROLE_ADMIN");
		roleWriter.setAuthority("ROLE_WRITER");
		roleUser.setAuthority("ROLE_USER");
		usersRegistration.setEmail(email);

		Optional<Users> optionalAdmin = userRepository.findByUsername("admin");
		if (optionalAdmin.isPresent()) {
			Users user = optionalAdmin.get();
			user.setPassword(passwordEncoder.encode(password));
			user.removeUsersRegistration();
			userRepository.flush();
			user.addUsersRegistration(usersRegistration);
			return;
		}

		admin.setUsername("admin");
		admin.setPassword(passwordEncoder.encode(password));

		userRepository.save(admin);

		admin.addRole(roleAdmin, roleWriter, roleUser);
		admin.addUsersRegistration(usersRegistration);
	}
}
