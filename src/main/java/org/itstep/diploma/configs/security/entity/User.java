package org.itstep.diploma.configs.security.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.itstep.diploma.registration.entity.UserRegistration;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user_table")
@Data
@EqualsAndHashCode(exclude = {"id", "authorities", "usersRegistration"})
@ToString
@Component
@Scope(scopeName = "prototype")
public final class User implements UserDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long id;
	@Column(name = "user_name", unique = true)
	private String username;
	private String password;
	@Column(name = "account_non_expired")
	private boolean accountNonExpired;
	@Column(name = "account_non_locked")
	private boolean accountNonLocked;
	@Column(name = "credentials_non_expired")
	private boolean credentialsNonExpired;
	private boolean enabled;
	@OneToMany(mappedBy = "user", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<Role> authorities = new HashSet<>();
	@OneToOne(mappedBy = "user", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private UserRegistration userRegistration;

	public void addRole(Role... roles) {
		Arrays.stream(roles).forEach(role -> {
			authorities.add(role);
			role.setUser(this);
		});
	}

	public void removeRole(Role role) {
		authorities.remove(role);
		role.setUser(null);
	}

	public void addUsersRegistration(UserRegistration userRegistration) {
		userRegistration.setUser(this);
		this.userRegistration = userRegistration;
	}

	public void removeUsersRegistration() {
		if (userRegistration != null) {
			userRegistration.setUser(null);
			this.userRegistration = null;
		}
	}
}