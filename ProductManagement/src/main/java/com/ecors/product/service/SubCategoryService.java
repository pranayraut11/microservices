package com.ecors.product.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;

import com.ecors.core.dto.ServiceResponse;
import com.ecors.product.DTO.ProductDTO;
import com.ecors.product.DTO.SubCategoryDTO;
import com.ecors.product.entity.Offer;
import com.ecors.product.entity.Product;

public interface SubCategoryService {

	SubCategoryDTO get(int id, boolean active);

	Optional<List<SubCategoryDTO>> getAllSubCateogry(boolean active);

	Optional<List<SubCategoryDTO>> getAllSubCateogry(Offer offer, boolean active);

	List<ProductDTO> getAllProductsBySubCateogry(int id, boolean active, Pageable page);

}
