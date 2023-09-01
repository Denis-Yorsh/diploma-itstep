package org.itstep.diploma.contact.message.dto;

import lombok.Data;

@Data
public final class ContactMessageDto {
	private Long id;
	private String name;
	private String email;
	private String massage;
}
