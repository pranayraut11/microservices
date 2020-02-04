package com.ecors.product.service;

import java.util.List;
import java.util.Optional;

import com.ecors.product.DTO.OfferDTO;

public interface OfferService {

	Optional<List<OfferDTO>> listOfferByOfferName(String offerName);

}
