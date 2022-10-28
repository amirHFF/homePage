package com.homepage.home.service.userAccount;

import com.homepage.home.repository.user.IAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AccountUserDetailsService implements UserDetailsService {

	@Autowired
	private IAccountRepository accountRepository;

	@Override
	public UserDetails loadUserByUsername(String user) throws UsernameNotFoundException {
		UserDetails userDetails = accountRepository.findByUserName(user);
		if (userDetails == null)
			throw new RuntimeException("user does not exist");
			return userDetails;
	}

}
