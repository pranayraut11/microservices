package com.ecors.product.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "subCategory")
	private Set<ProductSubCategory> products;

	@OneToMany(mappedBy = "subCategory")
	private Set<SubCategorySpecification> subCategorySpecifications;

	@OneToMany(mappedBy = "subCategory")
	private Set<OfferSubCategory> offerSubcategories;

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

	public Set<ProductSubCategory> getProducts() {
		return products;
	}

	public void setProducts(Set<ProductSubCategory> products) {
		this.products = products;
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
