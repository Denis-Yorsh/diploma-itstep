package org.itstep.diploma.contact.message.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.stereotype.Component;

@Entity
@Table(name = "contact_message_table")
@Data
@Component
public final class ContactMessage {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(length = 50, nullable = false)
	private String name;
	@Column(length = 100, nullable = false)
	private String email;
	@Column(length = 1000, nullable = false)
	private String massage;
}
