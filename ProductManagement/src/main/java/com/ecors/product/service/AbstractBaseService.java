package com.ecors.product.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public class AbstractBaseService<R> implements CrudRepository<R, Integer> {

	ServiceResponse<R,D> get(int id,boolean inActive,boolean dtoInResponse) {

		return findById(id);

	}

	List<R> getAll(boolean active) {
		return StreamSupport.stream(findAll().spliterator(), true).collect(Collectors.toList());

	}

	@Override
	public <S extends R> S save(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends R> Iterable<S> saveAll(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<R> findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean existsById(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterable<R> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<R> findAllById(Iterable<Integer> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(R entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll(Iterable<? extends R> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

}
