package com.homepage.home.model.user;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "HOME_PAGE_ACCOUNT")
@NoArgsConstructor
@AllArgsConstructor
public class Account extends SuperEntity implements UserDetails {
	@Id
	@SequenceGenerator(name = "AccountSeq", sequenceName = "ACCOUNT_SEQ", allocationSize = 1)
	@GeneratedValue(generator = "AccountSeq", strategy = GenerationType.SEQUENCE)
	private Long id;
	@Column(length = 25, nullable = false,unique = true)
	private String username;
	@Column(nullable = false)
	private String password;
	@OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL}, mappedBy = "account")
	private List<Role> authorityList = new ArrayList<>();
	@Column(length = 1)
	private boolean isExpired;
	@Column(length = 1, nullable = false)
	private boolean isEnabled;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorityList;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return !this.isExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return !isExpired && isEnabled;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return this.isEnabled;
	}

	public void setEnabled(boolean enabled) {
		isEnabled = enabled;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isExpired() {
		return isExpired;
	}

	@PrePersist
	public void persistInitialize() {
		setInsertTime(new Date(System.currentTimeMillis()));
		setInsertUser("amir");
	}

	@PreUpdate
	public void updateInitialize() {
		setUpdateTime(new Date(System.currentTimeMillis()));
		setVersion(getVersion() + 1);
	}

	public List<Role> getAuthorityList() {
		return authorityList;
	}

	public void setAuthorityList(List<Role> authorityList) {
		this.authorityList = authorityList;
	}

	@Override
	public long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setExpired(boolean expired) {
		isExpired = expired;
	}
}
