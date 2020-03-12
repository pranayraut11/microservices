package com.ecors.product.entity;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Offer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer offerId;

	private String offerName;

	private String offerDescription;

	private boolean active;

	private LocalDateTime activeFrom;

	private LocalDateTime activeTo;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL,mappedBy = "offer")
	private Set<OfferSubCategory> offerSubCategory;

	public LocalDateTime getActiveFrom() {
		return activeFrom;
	}

	public void setActiveFrom(LocalDateTime activeFrom) {
		this.activeFrom = activeFrom;
	}

	public LocalDateTime getActiveTo() {
		return activeTo;
	}

	public void setActiveTo(LocalDateTime activeTo) {
		this.activeTo = activeTo;
	}

	public Set<OfferSubCategory> getOfferSubCategory() {
		return offerSubCategory;
	}

	public void setOfferSubCategory(Set<OfferSubCategory> offerSubCategory) {
		this.offerSubCategory = offerSubCategory;
	}

	public Integer getOfferId() {
		return offerId;
	}

	public void setOfferId(Integer offerId) {
		this.offerId = offerId;
	}

	public String getOfferName() {
		return offerName;
	}

	public void setOfferName(String offerName) {
		this.offerName = offerName;
	}

	public String getOfferDescription() {
		return offerDescription;
	}

	public void setOfferDescription(String offerDescription) {
		this.offerDescription = offerDescription;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

}
