package com.ecors.core.dto;

import java.util.List;

public class OrderDTO {
	private String orderId;
	private ProductDTO orderedProduct;
	private AddressDTO deliveryAddress;
	private List<OrderDeliveryStatusDTO> deliveryDetails;
	private List<OrderedProductDTO> otherOrderedProducts;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public ProductDTO getOrderedProduct() {
		return orderedProduct;
	}

	public void setOrderedProduct(ProductDTO orderedProduct) {
		this.orderedProduct = orderedProduct;
	}

	public AddressDTO getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(AddressDTO deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public List<OrderDeliveryStatusDTO> getDeliveryDetails() {
		return deliveryDetails;
	}

	public void setDeliveryDetails(List<OrderDeliveryStatusDTO> deliveryDetails) {
		this.deliveryDetails = deliveryDetails;
	}

	public List<OrderedProductDTO> getOtherOrderedProducts() {
		return otherOrderedProducts;
	}

	public void setOtherOrderedProducts(List<OrderedProductDTO> otherOrderedProducts) {
		this.otherOrderedProducts = otherOrderedProducts;
	}

}
