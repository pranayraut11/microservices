package com.ecors.product.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.ecors.core.dto.ServiceResponse;
import com.ecors.core.utility.ModelMapperUtils;
import com.netflix.discovery.converters.Auto;

@Component
public abstract class AbstractBaseService<R, D> {

	@Autowired
	private CrudRepository<R, Integer> repo;
	
	

	public ServiceResponse<R, D> get(int id,Class<D> dtoClass, boolean inActive, boolean dtoInResponse) {
		ServiceResponse<R, D> res = new ServiceResponse<>();
		Optional<R> response = repo.findById(id);
		Assert.isTrue(response.isPresent(), "Entity not found");
		res.setEntity(response.get());
		if (dtoInResponse) {
			res.setDTO(ModelMapperUtils.map(response.get(), dtoClass));
			return res;
		}
		return res;

	}

	@SuppressWarnings("unchecked")
	public ServiceResponse<R, D> getAll(Class<D> dto,boolean inActive, boolean dtoInResponse) {
		ServiceResponse<R, D> res = new ServiceResponse<>();
		List<R> response = StreamSupport.stream(repo.findAll().spliterator(), true).collect(Collectors.toList());
		Collection<R> list = new ArrayList<>();
		list.addAll(response);
		res.setEntityList(list);
		if (dtoInResponse) {
			res.setDTOList((List<D>) ModelMapperUtils.mapAll(response, dto.getClass()).get());
			return res;
		}
		return res;

	}

}
