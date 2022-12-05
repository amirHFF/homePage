package com.homepage.home.controller;

import com.homepage.home.dto.*;
import com.homepage.home.mapper.AccountMapper;
import com.homepage.home.model.user.Account;
import com.homepage.home.model.user.Role;
import com.homepage.home.service.userAccount.IAccountService;
import com.homepage.home.service.userAccount.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/accounts")
public class AccountsController {

	private IAccountService service;
	@Autowired
	private IRoleService roleService;
	@Autowired
	private AccountMapper accountMapper;

	public AccountsController(IAccountService service) {
		this.service = service;
	}

	@PostMapping
	public ResponseEntity<String> saveAccount(@RequestBody AccountRequestDto requestDto) {
		AccountDto savedAccount = service.save(new AccountDto(requestDto.getUsername(), requestDto.getPassword()));
		return new ResponseEntity<>(savedAccount.getUsername(), HttpStatus.OK);
	}

	@PreAuthorize("hasRole('USER')")
	@GetMapping
	public ResponseEntity<List<AccountResponseDto>> getAllAccount() {
		List<AccountDto> loadAccounts = service.getAllAccount();
		return new ResponseEntity<>(loadAccounts.stream().map(accountMapper::dtoToResponse).collect(Collectors.toList()), HttpStatus.OK);
	}
	@PreAuthorize("hasRole('USER')")
	@PatchMapping(path = "{username}")
	public ResponseEntity<AccountResponseDto> updateAccount(@PathVariable String username, @RequestBody AccountPatchRequestDto patchRequestDto) {
		AccountDto updatedAccountDto = service.update(username,accountMapper.patchToDto(patchRequestDto));
		return new ResponseEntity<>(accountMapper.dtoToResponse(updatedAccountDto), HttpStatus.OK);
	}

	@PostMapping(path = "/roles")
	public ResponseEntity<RoleDto> helloWorld(@RequestBody RoleDto requestDto) {
		RoleDto savedRole = roleService.save(new RoleDto(requestDto.getAuthority(), requestDto.getAccountId()));
		return new ResponseEntity<>(savedRole, HttpStatus.OK);
	}

}
