package com.ecors.product.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.ecors.product.DTO.ProductDTO;
import com.ecors.product.DTO.SubCategoryDTO;
import com.ecors.product.entity.OfferSubCategory;

public interface SubCategoryService {

	SubCategoryDTO get(int id, boolean active);

	List<SubCategoryDTO> getAllSubCateogry(boolean active);

	List<SubCategoryDTO> getAllSubCateogry(OfferSubCategory offerSubcategory, boolean active);

	List<ProductDTO> getAllProductsBySubCateogry(int id, boolean active, Pageable page);

}
