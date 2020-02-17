package com.ecors.product.service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.ecors.core.dto.ServiceResponse;
import com.ecors.product.DTO.OfferDTO;
import com.ecors.product.DTO.SubCategoryDTO;
import com.ecors.product.entity.Offer;
import com.ecors.product.repository.OfferRepository;

@Service
public class OfferServiceImpl extends AbstractBaseService<Offer, OfferDTO> implements OfferService {

	private final static boolean DTO_REQUIRED = true;

	@Autowired
	private OfferRepository offerRepository;

	@Autowired
	private SubCategoryService subCategoryService;

	public ServiceResponse<Offer, OfferDTO> getOffer(String offerName, boolean active) {
		Optional<Offer> offer = offerRepository.findByActiveAndOfferName(active, offerName);
		Assert.isTrue(offer.isPresent(), "Invalid offername");
		ServiceResponse<Offer, OfferDTO> response = new ServiceResponse<>();
		// response.setEntity(offer.get());
		// response.setDTO(ModelMapperUtils.map(offer.get(), OfferDTO.class));
		return response;
	}

	@Override
	public Optional<Collection<OfferDTO>> getAll(boolean active) {
		return Optional.ofNullable(super.getAll(OfferDTO.class, active, DTO_REQUIRED).getDTOList());
	}

	@Override
	public Optional<List<SubCategoryDTO>> getAllSubCateogryByOffer(int offerid, boolean inActive) {
		ServiceResponse<Offer, OfferDTO> offer = super.get(offerid, OfferDTO.class, false, false);
		return subCategoryService.getAllSubCateogry(offer.getEntity().get(), inActive);
	}
}
