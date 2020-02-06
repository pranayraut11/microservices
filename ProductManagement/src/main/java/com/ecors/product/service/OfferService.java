package com.ecors.product.service;

import java.util.List;
import java.util.Optional;

import com.ecors.product.DTO.OfferDTO;
import com.ecors.product.DTO.OfferZoneDTO;

public interface OfferService {

	Optional<OfferZoneDTO> getByOfferName(String offerName, boolean active);

	Optional<List<OfferDTO>> getAll(boolean active);

}
