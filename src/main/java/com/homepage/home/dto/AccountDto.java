package com.homepage.home.dto;

import com.homepage.home.model.user.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class AccountDto {

	private Long id;
	private String username;
	private String password;
	private List<RoleDto> authorityList=new ArrayList<>();
	private boolean isExpired;
	private boolean isEnabled;

	public AccountDto(String username, String password) {
		this.username = username;
		this.password = password;
	}
}
