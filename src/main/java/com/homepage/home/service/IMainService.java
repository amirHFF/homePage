package com.homepage.home.service;

public interface IMainService<D> {
	D getById(long id);
	D save(D obj);
	D update(long id ,D obj);
	void delete(long id);
}
