package org.itstep.diploma.configs.security.configs;

import lombok.RequiredArgsConstructor;
import org.itstep.diploma.configs.security.entity.Roles;
import org.itstep.diploma.configs.security.entity.Users;
import org.itstep.diploma.configs.security.repository.UserRepository;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
public class SecurityUserDetails implements UserDetailsService {
	private final UserRepository userRepository;

	@Transactional(readOnly = true)
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Users> optionalUser = userRepository.findByUsername(username);
		if (optionalUser.isEmpty()) {
			throw new UsernameNotFoundException("User by username %s not found".formatted(username));
		}
		Users user = optionalUser.get();
		return User.withUsername(user.getUsername())
				.password(user.getPassword())
				.accountExpired(user.isAccountNonExpired())
				.accountLocked(user.isAccountNonLocked())
				.credentialsExpired(user.isCredentialsNonExpired())
				.disabled(user.isEnabled())
				.authorities(AuthorityUtils.createAuthorityList(
						user.getAuthorities().stream().map(Roles::getAuthority).toList()
				))
				.build();
	}
}