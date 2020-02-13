package com.ecors.core.dto;

import java.util.ArrayList;
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

	public void setDTOList(Collection<D> dTOList) {
		DTOList = dTOList;
	}

	public void setEntity(E entity) {
		if (entityList == null) {
			entityList = new ArrayList<>();
			entityList.add(entity);
		}
		this.entityList.add(entity);
	}

	public Collection<D> getDTOList() {
		return DTOList;
	}

	public void setDTO(D dTO) {
		if (DTOList == null) {
			DTOList = new ArrayList<>();
			DTOList.add(dTO);
		}
		this.DTOList.add(dTO);
	}

	public Optional<E> getEntity() {
		return entityList.stream().findFirst();
	}

	public Optional<D> getDTO() {
		return DTOList.stream().findFirst();
	}

}
