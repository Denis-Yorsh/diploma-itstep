package org.itstep.diploma.info.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "contact_massage_table")
@Data
public class ContactMassage {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String email;
	private String massage;
}
