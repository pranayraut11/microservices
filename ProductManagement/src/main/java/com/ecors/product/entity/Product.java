package com.ecors.product.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Product {

	@Id
	@GeneratedValue
	private Integer productID;
	private String productName;
	private String productDescription;
	private boolean active;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "product",cascade = CascadeType.ALL)
	private Set<ProductSubCategory> subCategories;

	
	
	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Set<ProductSubCategory> getSubCategories() {
		return subCategories;
	}

	public void setSubCategories(Set<ProductSubCategory> subCategories) {
		this.subCategories = subCategories;
	}

	public Integer getProductID() {
		return productID;
	}

	public void setProductID(Integer productID) {
		this.productID = productID;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

}
