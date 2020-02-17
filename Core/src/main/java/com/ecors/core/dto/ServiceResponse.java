package com.ecors.core.dto;

import java.util.Map;
import java.util.Optional;

public class ServiceResponse<E, D> {

	private Map<E, D> data;

	public Map<E, D> getData() {
		return data;
	}

	public void setData(Map<E, D> data) {
		this.data = data;

	}

	public Optional<E> getEntity() {
		return Optional.ofNullable(data.entrySet().stream().findFirst().get().getKey());
	}

	public Optional<D> getDTO() {
		return Optional.ofNullable(data.entrySet().stream().findFirst().get().getValue());
	}

}
