package com.ecors.product.service;

import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Pageable;

import com.ecors.product.DTO.ProductDTO;
import com.ecors.product.entity.Product;
import com.ecors.product.entity.SubCategory;

public interface ProductService {

	public Optional<ProductDTO> get(int id, boolean active);

	public Map<Product, ProductDTO> getAllBySubCategory(SubCategory subCategory, boolean inActive, Pageable page);

}
