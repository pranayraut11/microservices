package com.ecors.core.dto;

import java.time.LocalDateTime;

import com.ecors.core.enums.OrderStatus;

public class OrderedProductDTO {

	private Integer orderId;
	private String productID;
	private String productName;
	private String productDescription;
	private LocalDateTime deliveryDate;
	private OrderStatus orderStatus;

	public OrderedProductDTO() {
	}

	public OrderedProductDTO(Integer orderId, String productID, String productName, String productDescription,
			LocalDateTime deliveryDate, OrderStatus orderStatus) {
		super();
		this.orderId = orderId;
		this.productID = productID;
		this.productName = productName;
		this.productDescription = productDescription;
		this.deliveryDate = deliveryDate;
		this.orderStatus = orderStatus;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	public LocalDateTime getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(LocalDateTime deliveryDate) {
		this.deliveryDate = deliveryDate;
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
