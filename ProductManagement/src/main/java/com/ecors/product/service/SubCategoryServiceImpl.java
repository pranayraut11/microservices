package com.ecors.product.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.ecors.core.exception.NotFoundException;
import com.ecors.core.utility.ModelMapperUtils;
import com.ecors.product.DTO.ProductDTO;
import com.ecors.product.DTO.SubCategoryDTO;
import com.ecors.product.entity.Offer;
import com.ecors.product.entity.SubCategory;
import com.ecors.product.repository.SubCategoryRepository;

@Service
public class SubCategoryServiceImpl implements SubCategoryService {

	@Autowired
	private SubCategoryRepository subCategoryRepository;

	@Autowired
	private ProductService productService;

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
	public List<SubCategoryDTO> getAllSubCateogry(Offer offer, boolean active) {
		Optional<List<SubCategory>> subCategoryList = subCategoryRepository.findByActiveAndOffers(true, offer);
		Assert.isTrue(subCategoryList.isPresent(), "Subcategories not found for : " + offer.getOfferName());
		return ModelMapperUtils.mapAll(subCategoryList.get(), SubCategoryDTO.class);
	}

	@Override
	public List<ProductDTO> getAllProductsBySubCateogry(int id, boolean active, Pageable page) {
		return productService.getAllBySubCategory(subCategoryRepository.findById(id).get(), active, page);

	}

}
