package com.ecors.core.dto;

import java.time.LocalDateTime;

import com.ecors.core.enums.OrderStatus;

public class OrderDeliveryStatusDTO {
	private Long orderStatusId;

	private OrderStatus deliveryStatus;

	private LocalDateTime date;

	private String description;

	public Long getOrderStatusId() {
		return orderStatusId;
	}

	public void setOrderStatusId(Long orderStatusId) {
		this.orderStatusId = orderStatusId;
	}

	public OrderStatus getDeliveryStatus() {
		return deliveryStatus;
	}

	public void setDeliveryStatus(OrderStatus deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
