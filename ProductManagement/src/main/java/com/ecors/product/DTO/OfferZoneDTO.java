package com.ecors.product.DTO;

import java.util.List;

public class OfferZoneDTO extends OfferDTO {

	public OfferZoneDTO(OfferDTO offerDTO) {
		super(offerDTO.getOfferName(), offerDTO.getOfferId(), offerDTO.getOfferDescription());
		// TODO Auto-generated constructor stub
	}

	private List<SubCategoryDTO> subCategories;

	public List<SubCategoryDTO> getSubCategories() {
		return subCategories;
	}

	public void setSubCategories(List<SubCategoryDTO> subCategories) {
		this.subCategories = subCategories;
	}

}
