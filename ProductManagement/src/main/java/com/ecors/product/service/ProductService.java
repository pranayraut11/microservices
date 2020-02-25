package com.ecors.product.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.ecors.product.DTO.OrderSummary;
import com.ecors.product.DTO.ProductDTO;
import com.ecors.product.entity.SubCategory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

public interface ProductService {

	public ProductDTO get(int id, boolean active) throws JsonMappingException, JsonProcessingException;

	public List<ProductDTO> getAllBySubCategory(SubCategory subCategory, boolean inActive, Pageable page);

	public OrderSummary getProductOrderSummary(List<Integer> productIds);

}
