package com.ecors.product.entity;

import java.util.Set;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.ecors.core.enums.ProductStatus;

@Entity
@Cacheable
public class Product {

	@Id
	@GeneratedValue
	private Integer id;
	private String productId;
	private String productName;
	private String productImgUrl;
	private ProductStatus status;
	private int price;
	private String sellerId;

	@Embedded
	private ProductDetails productDetails;

	@Embedded
	private ProductDeliveryDetails productDeliveryDetails;

	public ProductDetails getProductDetails() {
		return productDetails;
	}

	public void setProductDetails(ProductDetails productDetails) {
		this.productDetails = productDetails;
	}

	public ProductDeliveryDetails getProductDeliveryDetails() {
		return productDeliveryDetails;
	}

	public void setProductDeliveryDetails(ProductDeliveryDetails productDeliveryDetails) {
		this.productDeliveryDetails = productDeliveryDetails;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	private short expectedDeliveryInDays;
	private String subCategory;

	public ProductStatus getStatus() {
		return status;
	}

	public void setStatus(ProductStatus status) {
		this.status = status;
	}

	public short getExpectedDeliveryInDays() {
		return expectedDeliveryInDays;
	}

	public void setExpectedDeliveryInDays(short expectedDeliveryInDays) {
		this.expectedDeliveryInDays = expectedDeliveryInDays;
	}

	public String getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(String subCategory) {
		this.subCategory = subCategory;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "product", cascade = CascadeType.ALL)
	private Set<ProductSubCategory> subCategories;

	@OneToMany(mappedBy = "product")
	private Set<ProductImages> productImages;

	public String getSellerId() {
		return sellerId;
	}

	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
	}

	public Set<ProductImages> getProductImages() {
		return productImages;
	}

	public void setProductImages(Set<ProductImages> productImages) {
		this.productImages = productImages;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getProductImgUrl() {
		return productImgUrl;
	}

	public void setProductImgUrl(String productImgUrl) {
		this.productImgUrl = productImgUrl;
	}

	public Set<ProductSubCategory> getSubCategories() {
		return subCategories;
	}

	public void setSubCategories(Set<ProductSubCategory> subCategories) {
		this.subCategories = subCategories;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

}
