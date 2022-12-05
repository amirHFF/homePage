package com.homepage.home.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


public class RoleDto {

	private long id;
	private String authority;
	private long accountId;

	public RoleDto(String authority, long accountId) {
		this.authority = authority;
		this.accountId = accountId;
	}

	public RoleDto() {
	}

	public RoleDto(String authority) {
		this.authority = authority;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public long getAccountId() {
		return accountId;
	}

	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}
}
