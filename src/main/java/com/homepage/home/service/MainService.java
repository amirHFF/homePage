package com.homepage.home.service;

import com.homepage.home.repository.IMainRepository;
import com.homepage.home.repository.MainRepositoryImpl;
import com.homepage.home.service.IMainService;
import org.springframework.stereotype.Service;

@Service
public class MainService<D> implements IMainService<D> {

	private IMainRepository repository;

	public void setRepository(IMainRepository repository) {
		this.repository=repository;
	}

	@Override
	public D getById(long id) {

		return null;
	}

	@Override
	public D save(D obj) {
		return null;
	}

	@Override
	public D update(long id, D obj) {
		return null;
	}

	@Override
	public void delete(long id) {

	}
}
