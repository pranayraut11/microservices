package com.ecors.product.DTO;

import java.util.Map;

public class SpecificationDTO {

	private String titile;
	private Map<String, String> data;

	public String getTitile() {
		return titile;
	}

	public void setTitile(String titile) {
		this.titile = titile;
	}

	public Map<String, String> getData() {
		return data;
	}

	public void setData(Map<String, String> data) {
		this.data = data;
	}

}
