package com.ecors.product.service;

import java.util.Optional;

import com.ecors.product.DTO.SubCategoryDTO;

public interface SubCategoryService {

	Optional<SubCategoryDTO> getSubCateogry(boolean active);
	
	Optional<SubCategoryDTO> getAllSubCateogry(boolean active);
	
	Optional<SubCategoryDTO> getAllSubCateogryByOffername(String offername,boolean active);

}
