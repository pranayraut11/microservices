package com.ecors.product.service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import com.ecors.core.dto.ServiceResponse;
import com.ecors.product.DTO.OfferDTO;
import com.ecors.product.DTO.SubCategoryDTO;
import com.ecors.product.entity.Offer;

public interface OfferService {

	Optional<Collection<OfferDTO>> getAll(boolean active);

	ServiceResponse<Offer, OfferDTO> getOffer(String offerName, boolean active);
	
	Optional<List<SubCategoryDTO>> getAllSubCateogryByOffer(int offer,boolean inActive);

}
