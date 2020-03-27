package com.ecors.product.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.ecors.core.dto.OrderSummary;
import com.ecors.core.dto.ProductDTO;
import com.ecors.core.enums.ProductSearchCriteria;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

public interface ProductService {

	public ProductDTO get(int id, boolean active) throws JsonMappingException, JsonProcessingException;

	public List<ProductDTO> getAllBySubCategory(String subCategory, boolean inActive, Pageable page);

	public OrderSummary getProductOrderSummary(List<Integer> productIds);

	public List<ProductDTO> getProductsByIds(List<Integer> productIds, ProductSearchCriteria by);

	public String addProduct(ProductDTO productDTO, String sellerId);
}
