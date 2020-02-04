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

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "OfferZone", joinColumns = {
			@JoinColumn(name = "offerId", updatable = false, nullable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "subCategoryId", updatable = false, nullable = false) })
	private Set<SubCategory> subCategories;

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

	public Set<SubCategory> getSubCategories() {
		return subCategories;
	}

	public void setSubCategories(Set<SubCategory> subCategories) {
		this.subCategories = subCategories;
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
