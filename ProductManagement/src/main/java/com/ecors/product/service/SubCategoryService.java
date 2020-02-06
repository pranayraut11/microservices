package com.ecors.product.service;

import java.util.List;
import java.util.Optional;

import com.ecors.product.DTO.SubCategoryDTO;
import com.ecors.product.entity.Offer;

public interface SubCategoryService {

	Optional<SubCategoryDTO> getSubCateogry(boolean active);
	
	Optional<List<SubCategoryDTO>> getAllSubCateogry(boolean active);
	
	Optional<List<SubCategoryDTO>> getAllSubCateogry(Offer offer,boolean active);

}
