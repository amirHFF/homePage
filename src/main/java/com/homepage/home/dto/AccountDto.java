package com.homepage.home.dto;

import com.homepage.home.model.user.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
public class AccountDto {

	private Long id;
	private String username;
	private String password;
	private Date insertTime;
	private List<RoleDto> authorityList=new ArrayList<>();
	private boolean isExpired;
	private boolean isEnabled;

	public AccountDto(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getInsertTime() {
		return insertTime;
	}

	public void setInsertTime(Date insertTime) {
		this.insertTime = insertTime;
	}

	public List<RoleDto> getAuthorityList() {
		return authorityList;
	}

	public void setAuthorityList(List<RoleDto> authorityList) {
		this.authorityList = authorityList;
	}

	public boolean isExpired() {
		return isExpired;
	}

	public void setExpired(boolean expired) {
		isExpired = expired;
	}

	public boolean isEnabled() {
		return isEnabled;
	}

	public void setEnabled(boolean enabled) {
		isEnabled = enabled;
	}
}
