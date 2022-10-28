package com.homepage.home.model.user;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "HOME_PAGE_ACCOUNT", schema = "HP_DB")
@NoArgsConstructor
@AllArgsConstructor
public class Account extends SuperEntity implements UserDetails {
	@Id
	@SequenceGenerator(name = "AccountSeq", sequenceName = "ACCOUNT_SEQ", allocationSize = 1)
	@GeneratedValue(generator = "AccountSeq", strategy = GenerationType.SEQUENCE)
	private Long id;
	@Column(length = 25, nullable = false)
	private String userName;
	@Column(length = 25, nullable = false)
	private String password;
	@OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.REMOVE, CascadeType.PERSIST }, mappedBy = "account")
	private List<Role> authorityList;
	@Column(length = 1)
	private boolean isExpired;
	@Column(length = 1, nullable = false)
	private boolean isEnabled;

	public void setAuthorityList(List<Role> authorityList) {
		this.authorityList = authorityList;
	}

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
		return this.userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		return this.isExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return false;
	}

	@Override
	public boolean isEnabled() {
		return this.isEnabled;
	}

	@Override
	public long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
