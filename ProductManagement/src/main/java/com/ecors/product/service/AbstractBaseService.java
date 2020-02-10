package com.ecors.product.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.ecors.core.dto.ServiceResponse;
import com.ecors.core.utility.ModelMapperUtils;

@Component
public abstract class AbstractBaseService<R, D> implements CrudRepository<R, Integer>{

	D dto;

	public ServiceResponse<R, D> get(int id, boolean inActive, boolean dtoInResponse) {
		ServiceResponse<R, D> res = new ServiceResponse<>();
		Optional<R> response = findById(id);
		ArrayList<R> list = new ArrayList<>();
		list.add(response.get());
		res.setEntity(list);
		if (dtoInResponse) {
			ArrayList<D> dtoList = new ArrayList<>();
			dtoList.add(ModelMapperUtils.map(response.get(), dto));
			res.setDTO(dtoList);
			return res;
		}
		return res;

	}

	@SuppressWarnings("unchecked")
	public ServiceResponse<R, D> getAll(boolean inActive, boolean dtoInResponse) {
		ServiceResponse<R, D> res = new ServiceResponse<>();
		List<R> response = StreamSupport.stream(findAll().spliterator(), true).collect(Collectors.toList());
		Collection<R> list = new ArrayList<>();
		list.addAll(response);
		res.setEntity(list);
		if (dtoInResponse) {
			res.setDTO((List<D>) ModelMapperUtils.mapAll(response, dto.getClass()).get());
			return res;
		}
		return res;

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
