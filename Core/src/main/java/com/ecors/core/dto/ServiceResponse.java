package com.ecors.core.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ServiceResponse<E, D> {

	private Map<E, D> data;
	private Optional<List<D>> dtoList;

	public Optional<List<D>> getDtoList() {
		return dtoList;
	}

	public void setDtoList(Optional<List<D>> dtoList) {
		this.dtoList = dtoList;
	}

	public Map<E, D> getData() {
		return data;
	}

	public void setData(Map<E, D> data) {
		this.data = data;

	}

	public List<E> getEntityList() {
		return new ArrayList<>(data.keySet());
	}

	public Optional<E> getEntity() {
		return Optional.ofNullable(data.entrySet().stream().findFirst().get().getKey());
	}

	public Optional<D> getDTO() {
		return Optional.ofNullable(data.entrySet().stream().findFirst().get().getValue());
	}

}
