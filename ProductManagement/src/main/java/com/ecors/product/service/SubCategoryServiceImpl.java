package com.ecors.product.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecors.core.utility.ModelMapperUtils;
import com.ecors.product.DTO.SubCategoryDTO;
import com.ecors.product.entity.SubCategory;
import com.ecors.product.repository.SubCategoryRepository;

@Service
public class SubCategoryServiceImpl implements SubCategoryService {

	@Autowired
	private SubCategoryRepository subCategoryRepository;

	@Override
	public Optional<SubCategoryDTO> getSubCateogry(boolean active) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<SubCategoryDTO> getAllSubCateogry(boolean active) {
		Optional<List<SubCategory>> subCategoryList = subCategoryRepository.findByActive(true);
		ModelMapperUtils.mapAll(subCategoryList.get(), SubCategory.class);
		return null;
	}

	@Override
	public Optional<SubCategoryDTO> getAllSubCateogryByOffername(String offername, boolean active) {
		// TODO Auto-generated method stub
		return null;
	}

}
