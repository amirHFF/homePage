package com.homepage.home.service.userAccount;

import com.homepage.home.dto.AccountDto;
import com.homepage.home.dto.RoleDto;
import com.homepage.home.mapper.AccountMapper;
import com.homepage.home.model.user.Account;
import com.homepage.home.model.user.Role;
import com.homepage.home.repository.user.IAccountRepository;
import com.homepage.home.repository.user.IRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountService implements IAccountService {
	private PasswordEncoder passwordEncoder;
	private IAccountRepository repository;
	private AccountMapper accountMapper;
	private IRoleRepository roleRepository;

	public AccountService(PasswordEncoder passwordEncoder, IAccountRepository repository, AccountMapper accountMapper, IRoleRepository roleRepository) {
		this.passwordEncoder = passwordEncoder;
		this.repository = repository;
		this.accountMapper = accountMapper;
		this.roleRepository = roleRepository;
	}

	@Override
	public AccountDto getById(long id) {
		return null;
	}

	@Override
	@Transactional
	public AccountDto save(AccountDto account) {
		account.setPassword(passwordEncoder.encode(account.getPassword()));
		Account saved = repository.save(accountMapper.dtoToEntity(account));
		return accountMapper.entityToDto(saved);
	}

	@Override
	@Transactional
	public AccountDto update(Object username, AccountDto accountDto) {
		Account loadedAccount = repository.findByUserName((String) username);
		if (loadedAccount != null) {
			if (accountDto.getUsername() != null && !accountDto.getUsername().isEmpty())
				loadedAccount.setUsername(accountDto.getUsername());
			if (accountDto.getAuthorityList() != null && !accountDto.getAuthorityList().isEmpty()) {
				Role newRole=new Role(accountDto.getAuthorityList().get(0).getAuthority(),loadedAccount);
				if (!loadedAccount.getAuthorityList().contains(newRole)){
					loadedAccount.getAuthorityList().add(newRole);
				}
			}

			loadedAccount.setExpired(accountDto.isExpired());
			loadedAccount.setEnabled(accountDto.isEnabled());

			repository.update(loadedAccount);
		}
		return accountMapper.entityToDto(loadedAccount);
	}

	@Override
	public void delete(long id) {

	}

	@Override
	@Transactional
	public void addRoleToUser(long id, String role) {
		Account loadedAccount = (Account) repository.find(id);
		loadedAccount.getAuthorityList().add(new Role(role, loadedAccount));
		repository.update(loadedAccount);
	}

	@Override
	public void loadAsEntity(long id) {
	}

	@Override
	public List<AccountDto> getAllAccount() {
		return repository.findAll().stream().map(accountMapper::entityToDto).collect(Collectors.toList());
	}
}
