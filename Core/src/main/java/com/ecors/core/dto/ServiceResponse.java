package com.ecors.core.dto;

import java.util.Collection;
import java.util.Optional;

public class ServiceResponse<E, D> {

	private Collection<E> entityList;
	private Collection<D> DTOList;

	public Collection<E> getEntityList() {
		return entityList;
	}

	public void setEntityList(Collection<E> entityList) {
		this.entityList = entityList;
	}

	public Collection<D> getDTOList() {
		return DTOList;
	}

	public void setDTOList(Collection<D> dTOList) {
		DTOList = dTOList;
	}

	public Optional<E> getEntity() {
		return entityList.stream().findFirst();
	}

	public Optional<D> getDTO() {
		return DTOList.stream().findFirst();
	}

}
