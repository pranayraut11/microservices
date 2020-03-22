package com.ecors.category.service;

import java.util.List;

import com.ecors.category.entity.OfferSubCategory;
import com.ecors.core.dto.SubCategoryDTO;

public interface SubCategoryService {

	SubCategoryDTO get(int id, boolean active);

	List<SubCategoryDTO> getAllSubCateogry(boolean active);

	List<SubCategoryDTO> getAllSubCateogry(OfferSubCategory offerSubcategory, boolean active);

	List<SubCategoryDTO> getAllSubCateogryByOffer(int offer, boolean inActive);

}
