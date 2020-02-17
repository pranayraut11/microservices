package com.ecors.product.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.ecors.product.DTO.ProductDTO;
import com.ecors.product.entity.SubCategory;

public interface ProductService {

	public ProductDTO get(int id, boolean active);

	public List<ProductDTO> getAllBySubCategory(SubCategory subCategory, boolean inActive, Pageable page);

}
