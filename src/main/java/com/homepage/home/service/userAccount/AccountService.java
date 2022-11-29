package com.homepage.home.service.userAccount;

import com.homepage.home.dto.AccountDto;
import com.homepage.home.mapper.AccountMapper;
import com.homepage.home.model.user.Account;
import com.homepage.home.model.user.Role;
import com.homepage.home.repository.user.IAccountRepository;
import com.homepage.home.repository.user.IRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountService  implements IAccountService {
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private IAccountRepository repository;
	@Autowired
	private AccountMapper accountMapper;


	@Override
	public AccountDto getById(long id) {
		return null;
	}

	@Override
	@Transactional
	public AccountDto save(AccountDto account) {
		account.setPassword(passwordEncoder.encode(account.getPassword()));
		Account saved= repository.save(accountMapper.dtoToEntity(account));
		return accountMapper.entityToDto(saved);
	}

	@Override
	public AccountDto update(long id, AccountDto entity) {
		return null;
	}

	@Override
	public void delete(long id) {

	}

	@Override
	@Transactional
	public void addRoleToUser(long id, String role) {
		Account loadedAccount = (Account) repository.find(id);
		loadedAccount.getAuthorityList().add(new Role(role,loadedAccount));
		repository.update(loadedAccount);
	}

	@Override
	public void loadAsEntity(long id) {
	}
}
