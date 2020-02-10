package com.ecors.core.dto;

public class ServiceResponse<E, D> {

	private E entity;
	private D DTO;

	public E getEntity() {
		return entity;
	}

	public void setEntity(E entity) {
		this.entity = entity;
	}

	public D getDTO() {
		return DTO;
	}

	public void setDTO(D dTO) {
		DTO = dTO;
	}

}
