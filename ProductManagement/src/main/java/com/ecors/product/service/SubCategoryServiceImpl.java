package com.ecors.product.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.ecors.core.utility.ModelMapperUtils;
import com.ecors.product.DTO.OfferDTO;
import com.ecors.product.DTO.SubCategoryDTO;
import com.ecors.product.entity.Offer;
import com.ecors.product.entity.SubCategory;
import com.ecors.product.repository.SubCategoryRepository;

@Service
public class SubCategoryServiceImpl implements SubCategoryService {

	@Autowired
	private SubCategoryRepository subCategoryRepository;
	
	@Autowired
	private OfferService offerService;

	@Override
	public Optional<SubCategoryDTO> getSubCateogry(boolean active) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<List<SubCategoryDTO>> getAllSubCateogry(boolean active) {
		Optional<List<SubCategory>> subCategoryList = subCategoryRepository.findByActive(true);
		if (subCategoryList.isPresent()) {
			return ModelMapperUtils.mapAll(subCategoryList.get(), SubCategoryDTO.class);
		}
		return Optional.empty();
	}

	@Override
	public Optional<List<SubCategoryDTO>> getAllSubCateogry(String offername, boolean active) {
		ServiceResponse<Offer, OfferDTO> offer =offerService.getOffer(offername, active);
		Optional<List<SubCategory>> subCategoryList = subCategoryRepository.findByActiveAndOffers(true, offer.getEntity());
		Assert.isTrue(subCategoryList.isPresent(), "Subcategories not found for : " + offer.getOfferName());
		return ModelMapperUtils.mapAll(subCategoryList.get(), SubCategoryDTO.class);
	}

}
