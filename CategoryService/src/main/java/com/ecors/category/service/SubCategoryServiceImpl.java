package com.ecors.category.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.ecors.category.entity.OfferSubCategory;
import com.ecors.category.entity.SubCategory;
import com.ecors.category.repository.SubCategoryRepository;
import com.ecors.core.dto.SubCategoryDTO;
import com.ecors.core.exception.NotFoundException;
import com.ecors.core.utility.ModelMapperUtils;

@Service
public class SubCategoryServiceImpl implements SubCategoryService {

	@Autowired
	private SubCategoryRepository subCategoryRepository;

	@Override
	public SubCategoryDTO get(int id, boolean active) {
		return ModelMapperUtils.map(subCategoryRepository.findById(id), SubCategoryDTO.class);
	}

	@Override
	public List<SubCategoryDTO> getAllSubCateogry(boolean active) {
		Optional<List<SubCategory>> subCategoryList = subCategoryRepository.findByActive(true);
		if (subCategoryList.isPresent()) {
			return ModelMapperUtils.mapAll(subCategoryList.get(), SubCategoryDTO.class);
		}
		throw new NotFoundException("Subcategory");
	}

	@Override
	public List<SubCategoryDTO> getAllSubCateogry(OfferSubCategory offerSubcategory, boolean active) {
		Optional<List<SubCategory>> subCategoryList = subCategoryRepository.findByActiveAndOfferSubcategories(true,
				offerSubcategory);
		Assert.isTrue(subCategoryList.isPresent(),
				"Subcategories not found for : " + offerSubcategory.getSubCategory().getSubCategoryName());
		return ModelMapperUtils.mapAll(subCategoryList.get(), SubCategoryDTO.class);
	}

	@Override
	public List<SubCategoryDTO> getAllSubCateogryByOffer(int offer, boolean inActive) {
		// TODO Auto-generated method stub
		return null;
	}

}
