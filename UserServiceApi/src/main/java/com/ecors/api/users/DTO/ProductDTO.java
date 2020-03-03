package com.ecors.api.users.DTO;

import java.time.LocalDateTime;

public class ProductDTO {

	private String productID;
	private String productName;
	private String productDescription;
	private LocalDateTime deliveryDate;
	private boolean deliveryFeeDiscounted;

	public LocalDateTime getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(LocalDateTime deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public boolean isDeliveryFeeDiscounted() {
		return deliveryFeeDiscounted;
	}

	public void setDeliveryFeeDiscounted(boolean deliveryFeeDiscounted) {
		this.deliveryFeeDiscounted = deliveryFeeDiscounted;
	}

	public String getProductID() {
		return productID;
	}

	public void setProductID(String productID) {
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
