package com.ecors.product.entity;

import javax.persistence.Embeddable;

@Embeddable
public class ProductDeliveryDetails {

	private int discountedPrice;
	private short discount;
	private boolean deliveryFeeDiscounted;

	public int getDiscountedPrice() {
		return discountedPrice;
	}

	public void setDiscountedPrice(int discountedPrice) {
		this.discountedPrice = discountedPrice;
	}

	public short getDiscount() {
		return discount;
	}

	public void setDiscount(short discount) {
		this.discount = discount;
	}

	public boolean isDeliveryFeeDiscounted() {
		return deliveryFeeDiscounted;
	}

	public void setDeliveryFeeDiscounted(boolean deliveryFeeDiscounted) {
		this.deliveryFeeDiscounted = deliveryFeeDiscounted;
	}

}
