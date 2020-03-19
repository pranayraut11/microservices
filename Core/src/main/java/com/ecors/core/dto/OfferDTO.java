package com.ecors.core.dto;

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
