package com.ecors.product.DTO;

import java.util.List;

public class OrderSummary {
	private List<ProductDTO> product;

	private int totalAmmount;
	private int deliveryFee;
	private int savedAmmount;

	public List<ProductDTO> getProduct() {
		return product;
	}

	public void setProduct(List<ProductDTO> product) {
		this.product = product;
	}

	public int getTotalAmmount() {
		return totalAmmount;
	}

	public void setTotalAmmount(int totalAmmount) {
		this.totalAmmount = totalAmmount;
	}

	public int getDeliveryFee() {
		return deliveryFee;
	}

	public void setDeliveryFee(int deliveryFee) {
		this.deliveryFee = deliveryFee;
	}

	public int getSavedAmmount() {
		return savedAmmount;
	}

	public void setSavedAmmount(int savedAmmount) {
		this.savedAmmount = savedAmmount;
	}

}
