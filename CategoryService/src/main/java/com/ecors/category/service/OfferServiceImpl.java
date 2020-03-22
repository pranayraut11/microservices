package com.ecors.category.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.ecors.category.entity.Offer;
import com.ecors.category.entity.OfferSubCategory;
import com.ecors.category.repository.OfferRepository;
import com.ecors.core.dto.OfferDTO;
import com.ecors.core.dto.SubCategoryDTO;
import com.ecors.core.utility.ModelMapperUtils;

@Service
public class OfferServiceImpl implements OfferService {

	@Autowired
	private OfferRepository offerRepository;

	public OfferDTO getOffer(String offerName, boolean active) {
		Optional<Offer> offer = offerRepository.findByActiveAndOfferName(active, offerName);
		Assert.isTrue(offer.isPresent(), "Invalid offername");
		return ModelMapperUtils.map(offer.get(), OfferDTO.class);
	}

	@Override
	public List<OfferDTO> getAll(boolean active) {
		Optional<List<Offer>> offerList = offerRepository.findByActive(true);
		Assert.isTrue(offerList.isPresent(), "No valid offer available");
		return ModelMapperUtils.mapAll(offerList.get(), OfferDTO.class);
	}

	public List<SubCategoryDTO> getAllSubCateogryByOffer(int offerid, boolean inActive) {
		Optional<Offer> offer = offerRepository.findById(offerid);
		Set<OfferSubCategory> offerSubCategories = offer.get().getOfferSubCategory();
		return mapSubCategory(offerSubCategories);
	}

	public static List<SubCategoryDTO> mapSubCategory(final Set<OfferSubCategory> entityList) {
		return entityList.stream().map(entity -> ModelMapperUtils.map(entity.getSubCategory(), SubCategoryDTO.class))
				.collect(Collectors.toList());
	}
}
