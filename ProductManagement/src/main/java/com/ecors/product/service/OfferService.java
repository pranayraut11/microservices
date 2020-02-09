package com.ecors.product.service;

import java.util.List;
import java.util.Optional;

import com.ecors.product.DTO.OfferDTO;
import com.ecors.product.DTO.OfferZoneDTO;
import com.ecors.product.entity.Offer;

public interface OfferService {

	Optional<OfferZoneDTO> getByOfferName(String offerName, boolean active);

	Optional<List<OfferDTO>> getAll(boolean active);
	
	protected ServiceResponse<Offer, OfferDTO> getOffer(String offerName, boolean active);

}
