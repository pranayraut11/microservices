package com.ecors.product.service;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.ecors.core.dto.ServiceResponse;
import com.ecors.product.DTO.OfferDTO;
import com.ecors.product.entity.Offer;
import com.ecors.product.repository.OfferRepository;

@Service
public class OfferServiceImpl extends AbstractBaseService<Offer, OfferDTO> implements OfferService {

	private final static boolean ACTIVE = true;

	@Autowired
	private OfferRepository offerRepository;

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
		return Optional.ofNullable(super.getAll(active, true).getDTOList());
	}
}
