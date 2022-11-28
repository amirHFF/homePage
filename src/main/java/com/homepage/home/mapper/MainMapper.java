package com.homepage.home.mapper;

public interface MainMapper<E,D> {
	E dtoToEntity(D dto);
	D entityToDto(E entity);
}
