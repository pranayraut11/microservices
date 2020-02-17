package com.ecors.product.service;

import java.util.List;

import com.ecors.product.DTO.OfferDTO;
import com.ecors.product.DTO.SubCategoryDTO;

public interface OfferService {

	List<OfferDTO> getAll(boolean active);

	OfferDTO getOffer(String offerName, boolean active);

	List<SubCategoryDTO> getAllSubCateogryByOffer(int offer, boolean inActive);

}
