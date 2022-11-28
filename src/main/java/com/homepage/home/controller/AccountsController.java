package com.homepage.home.controller;

import com.homepage.home.dto.AccountDto;
import com.homepage.home.dto.AccountRequestDto;
import com.homepage.home.dto.RoleDto;
import com.homepage.home.model.user.Role;
import com.homepage.home.service.userAccount.IAccountService;
import com.homepage.home.service.userAccount.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/accounts")
public class AccountsController {

	private IAccountService service;
	@Autowired
	private IRoleService roleService;

	public AccountsController(IAccountService service) {
		this.service = service;
	}

	@PostMapping
	public ResponseEntity<AccountDto> saveAccount(@RequestBody AccountRequestDto requestDto) {
		AccountDto savedAccount = service.save(new AccountDto(requestDto.getUsername(), requestDto.getPassword()));
		return new ResponseEntity<>(savedAccount, HttpStatus.OK);
	}
	@PostMapping(path = "/roles")
	public ResponseEntity<RoleDto> helloWorld(@RequestBody RoleDto requestDto) {
		RoleDto savedRole = roleService.save(new RoleDto(requestDto.getAuthority(),requestDto.getAccount()));
		return new ResponseEntity<>(savedRole, HttpStatus.OK);
	}

}
