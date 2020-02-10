package com.ecors.product.service;

import java.util.List;
import java.util.Optional;

import com.ecors.core.dto.ServiceResponse;
import com.ecors.product.DTO.ProductDTO;
import com.ecors.product.entity.Product;

public interface ProductService {

	public Optional<ProductDTO> get(int id, boolean active);

	public ServiceResponse<List<Product>, List<ProductDTO>> getAllBySubCategory(String subCategory, boolean active);
}
