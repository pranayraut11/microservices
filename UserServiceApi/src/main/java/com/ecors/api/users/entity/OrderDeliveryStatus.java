package com.ecors.api.users.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.ecors.core.enums.OrderStatus;

@Entity
public class OrderDeliveryStatus {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long orderStatusId;

	@ManyToOne
	@JoinColumn(name = "userOrderId")
	private UserOrders userOrders;

	private OrderStatus deliveryStatus;

	private LocalDateTime date;

	private String description;

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public Long getOrderStatusId() {
		return orderStatusId;
	}

	public void setOrderStatusId(Long orderStatusId) {
		this.orderStatusId = orderStatusId;
	}

	

	public UserOrders getUserOrders() {
		return userOrders;
	}

	public void setUserOrders(UserOrders userOrders) {
		this.userOrders = userOrders;
	}

	public OrderStatus getDeliveryStatus() {
		return deliveryStatus;
	}

	public void setDeliveryStatus(OrderStatus deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
