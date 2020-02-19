package com.ecors.product.DTO;

import java.util.List;
import java.util.Map;

public class ProductDetails {
	private String productDescription;
	private Map<String, String> highlights;
	private String description;
	private List<ImageDTO> images;
	private List<SpecificationDTO> specifications;

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	

	public List<ImageDTO> getImages() {
		return images;
	}

	public void setImages(List<ImageDTO> images) {
		this.images = images;
	}

	public List<SpecificationDTO> getSpecifications() {
		return specifications;
	}

	public void setSpecifications(List<SpecificationDTO> specifications) {
		this.specifications = specifications;
	}

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
