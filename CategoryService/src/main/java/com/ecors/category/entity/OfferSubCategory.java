package com.ecors.category.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class OfferSubCategory {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer offerSubCategoryId;

	@ManyToOne
	@JoinColumn(name = "offerId")
	private Offer offer;

	@ManyToOne
	@JoinColumn(name = "subCategoryId")
	private SubCategory subCategory;

	public Integer getOfferSubCategoryId() {
		return offerSubCategoryId;
	}

	public void setOfferSubCategoryId(Integer offerSubCategoryId) {
		this.offerSubCategoryId = offerSubCategoryId;
	}

	public Offer getOffer() {
		return offer;
	}

	public void setOffer(Offer offer) {
		this.offer = offer;
	}

	public SubCategory getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(SubCategory subCategory) {
		this.subCategory = subCategory;
	}

}
