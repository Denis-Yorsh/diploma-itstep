package org.itstep.diploma.info.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Data;
import org.springframework.stereotype.Component;

@Entity
@Table(name = "contact_massage_table")
@Data
@Component
public final class ContactMessage {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
//	@Email
	private String email;
	private String massage;
}
