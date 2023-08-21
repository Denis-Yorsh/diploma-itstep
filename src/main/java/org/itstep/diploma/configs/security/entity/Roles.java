package org.itstep.diploma.configs.security.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

@Entity
@Table(name = "roles_table")
@Data
@EqualsAndHashCode(exclude = {"id", "user"})
@Component
@Scope(scopeName = "prototype")
public final class Roles implements GrantedAuthority {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "role_id")
	private Long id;
	@Column(name = "role")
	private String authority;
	@ManyToOne
	@JoinColumn(name = "fk_user_id", nullable = false)
	private Users user;
}