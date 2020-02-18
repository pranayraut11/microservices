package com.ecors.product.DTO;

import java.util.Map;

public class ProductDetails {

	private Map<String, String> highlights;
	private String description;

	public Map<String, String> getHighlights() {
		return highlights;
	}

	public void setHighlights(Map<String, String> highlights) {
		this.highlights = highlights;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
