package com.homepage.home.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Setter
@Getter
public class AccountRequestDto {
	@NotNull(message = "username is mandatory")
	private String username;

	@NotNull(message = "password is mandatory")
	private String password;
}
