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
import com.ecors.product.repository.OfferRepository;

@Service
public class OfferServiceImpl implements OfferService {

	@Autowired
	private OfferRepository offerRepository;

	@Autowired
	private SubCategoryService subCategoryService;

	public OfferDTO getOffer(String offerName, boolean active) {
		Optional<Offer> offer = offerRepository.findByActiveAndOfferName(active, offerName);
		Assert.isTrue(offer.isPresent(), "Invalid offername");
		return ModelMapperUtils.map(offer.get(), OfferDTO.class);
	}

	@Override
	public List<OfferDTO> getAll(boolean active) {
		return ModelMapperUtils.mapAll(offerRepository.findByActive(true).get(), OfferDTO.class);
	}

	@Override
	public List<SubCategoryDTO> getAllSubCateogryByOffer(int offerid, boolean inActive) {
		Optional<Offer> offer = offerRepository.findById(offerid);
		return subCategoryService.getAllSubCateogry(offer.get(), inActive);
	}
}
