package com.ecors.category.service;

import java.util.List;

import com.ecors.core.dto.OfferDTO;

public interface OfferService {

	List<OfferDTO> getAll(boolean active);

	OfferDTO getOffer(String offerName, boolean active);

	

}
