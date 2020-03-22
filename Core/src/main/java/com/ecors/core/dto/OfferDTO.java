package com.ecors.core.dto;

import java.util.List;

public class OfferDTO {

	public OfferDTO() {

	}

	public OfferDTO(String offerName, Integer offerId, String offerDescription) {

		this.offerName = offerName;
		this.offerId = offerId;
		this.offerDescription = offerDescription;
	}

	private String offerName;

	private Integer offerId;

	private String offerDescription;

	private List<SubCategoryDTO> subCategories;

	public List<SubCategoryDTO> getSubCategories() {
		return subCategories;
	}

	public void setSubCategories(List<SubCategoryDTO> subCategories) {
		this.subCategories = subCategories;
	}

	public String getOfferName() {
		return offerName;
	}

	public void setOfferName(String offerName) {
		this.offerName = offerName;
	}

	public Integer getOfferId() {
		return offerId;
	}

	public void setOfferId(Integer offerId) {
		this.offerId = offerId;
	}

	public String getOfferDescription() {
		return offerDescription;
	}

	public void setOfferDescription(String offerDescription) {
		this.offerDescription = offerDescription;
	}

}
