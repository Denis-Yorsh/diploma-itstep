package org.itstep.diploma.info.dto;

import lombok.Data;

@Data
public final class ContactMessageDto {
	private Long id;
	private String name;
	private String email;
	private String massage;
}
