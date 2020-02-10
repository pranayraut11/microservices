package com.ecors.core.dto;

import java.util.Collection;
import java.util.List;

public class ServiceResponse<E, D> {

	private Collection<E> entity;
	private List<D> DTO;

	public Collection<E> getEntity() {
		return entity;
	}

	public void setEntity(Collection<E> entity) {
		this.entity = entity;
	}

	public List<D> getDTO() {
		return DTO;
	}

	public void setDTO(List<D> dTO) {
		DTO = dTO;
	}

}
