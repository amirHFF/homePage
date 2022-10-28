package com.homepage.home.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RoleDto {

	private long id;
	private String authority;
	private AccountDto account;

	public RoleDto(String authority, AccountDto account) {
		this.authority = authority;
		this.account = account;
	}
}
