package com.ecors.product.service;

import com.ecors.product.DTO.ProductDTO;

public interface ProductService  {

	
	public ProductDTO getAllBySubCategory(String subCategory,boolean active);
}
