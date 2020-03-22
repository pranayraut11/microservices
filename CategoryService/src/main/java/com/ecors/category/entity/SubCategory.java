package com.ecors.category.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class SubCategory {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer subCategoryId;

	private String subCategoryName;

	@ManyToOne
	@JoinColumn(name = "categoryId")
	private Category productCategory;

	@OneToMany(mappedBy = "subCategory")
	private Set<SubCategorySpecification> subCategorySpecifications;

	private String subCategoryImgUrl;

	private boolean active;

	private String offerMessage;

	public Set<SubCategorySpecification> getSubCategorySpecifications() {
		return subCategorySpecifications;
	}

	public void setSubCategorySpecifications(Set<SubCategorySpecification> subCategorySpecifications) {
		this.subCategorySpecifications = subCategorySpecifications;
	}

	public String getOfferMessage() {
		return offerMessage;
	}

	public void setOfferMessage(String offerMessage) {
		this.offerMessage = offerMessage;
	}

	public boolean getActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getSubCategoryImgUrl() {
		return subCategoryImgUrl;
	}

	public void setSubCategoryImgUrl(String subCategoryImgUrl) {
		this.subCategoryImgUrl = subCategoryImgUrl;
	}

	public Integer getSubCategoryId() {
		return subCategoryId;
	}

	public void setSubCategoryId(Integer subCategoryId) {
		this.subCategoryId = subCategoryId;
	}

	public String getSubCategoryName() {
		return subCategoryName;
	}

	public void setSubCategoryName(String subCategoryName) {
		this.subCategoryName = subCategoryName;
	}

	public Category getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(Category productCategory) {
		this.productCategory = productCategory;
	}

}
