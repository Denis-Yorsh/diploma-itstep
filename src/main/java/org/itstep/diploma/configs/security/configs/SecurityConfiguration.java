package org.itstep.diploma.configs.security.configs;

import org.itstep.diploma.configs.security.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@Profile(value = {"production", "developerH2"})
public class SecurityConfiguration {

	@Bean
	public UserDetailsService userDetailsService(UserRepository userRepository) {
		return new SecurityUserDetails(userRepository);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain securityFilterChain(final HttpSecurity http) throws Exception {
		return http
				.authorizeHttpRequests(authorize ->
						authorize
								.requestMatchers(
										"/",
										"/learnMore/**",
										"/info/**",
										"/message/**",
										"/registration/**",
										"/image/**",
										"/error/**"
								).permitAll()
								.requestMatchers("/blog/**").hasAuthority("ROLE_USER")
								.requestMatchers(
										"/admin/**",
										"/delete/**",
										"/homeRole/**"
								).hasAuthority("ROLE_ADMIN")
								.requestMatchers("/post/**").hasAuthority("ROLE_WRITER")
								.requestMatchers(
										"/css/**",
										"/images/**",
										"/js/**"
								).permitAll()
								.anyRequest().authenticated())
				.httpBasic(Customizer.withDefaults())
				.formLogin(form -> form.loginPage("/login").permitAll()
						.defaultSuccessUrl("/", true))
				.logout(logout -> logout.logoutUrl("/logout").permitAll()
						.logoutSuccessUrl("/"))
				.build();
	}
}