package com.ecors.product.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.ecors.core.dto.ServiceResponse;
import com.ecors.product.DTO.OfferDTO;
import com.ecors.product.DTO.OfferZoneDTO;
import com.ecors.product.entity.Offer;
import com.ecors.product.repository.OfferRepository;

@Service
public class OfferServiceImpl extends AbstractBaseService<Offer, OfferDTO> implements OfferService {

	private final static boolean ACTIVE = true;

	@Autowired
	private OfferRepository offerRepository;

	@Autowired
	private SubCategoryService subCategoryService;

	
//	@Override
//	public Optional<List<OfferDTO>> getAll(boolean inActive) {
//		Optional<List<Offer>> offer = offerRepository.findByActive(inActive);
//
//		if (offer.isPresent()) {
//			return ModelMapperUtils.mapAll(offer.get(), OfferDTO.class);
//		} else {
//			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No active offers available");
//		}
//
//	}

	public ServiceResponse<Offer, OfferDTO> getOffer(String offerName, boolean active) {
		Optional<Offer> offer = offerRepository.findByActiveAndOfferName(active, offerName);
		Assert.isTrue(offer.isPresent(), "Invalid offername");
		ServiceResponse<Offer, OfferDTO> response = new ServiceResponse<>();
		//response.setEntity(offer.get());
		//response.setDTO(ModelMapperUtils.map(offer.get(), OfferDTO.class));
		return response;
	}


	@Override
	public Optional<OfferZoneDTO> getByOfferName(String offerName, boolean active) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Optional<List<OfferDTO>> getAll(boolean active) {
		return Optional.ofNullable(super.getAll( active, true).getDTO());
	}
}
