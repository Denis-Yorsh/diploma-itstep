package org.itstep.diploma.registration.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.itstep.diploma.configs.security.entity.User;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_registration_table")
@Component
@Scope("prototype")
@Data
public final class UserRegistration {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_registration_id")
	private Long id;
	@Column(name = "first_name")
	private String firstName;
	@Column(name = "last_name")
	private String lastName;
	@Column(name = "day_birthday")
	private LocalDate dayBirthday;
	@Column(unique = true)
	private String email;
	@Column(name = "day_registration")
	private LocalDateTime dayRegistration;
	@OneToOne
	@JoinColumn(name = "fk_user_id", nullable = false)
	private User user;
}