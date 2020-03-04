package com.ecors.api.users.entity;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class UserOrders {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer userOrderId;

	@ManyToOne
	@JoinColumn(name = "orderId")
	private Orders order;

	@OneToMany(mappedBy = "userOrders",cascade = CascadeType.ALL)
	private Set<OrderDeliveryStatus> orderDeliveryStatus;

	private String productId;

	private boolean deliveryFeeDiscounted;

	private LocalDateTime deliveryDate;

	public LocalDateTime getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(LocalDateTime deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public Set<OrderDeliveryStatus> getOrderDeliveryStatus() {
		return orderDeliveryStatus;
	}

	public void setOrderDeliveryStatus(Set<OrderDeliveryStatus> orderDeliveryStatus) {
		this.orderDeliveryStatus = orderDeliveryStatus;
	}

	public boolean isDeliveryFeeDiscounted() {
		return deliveryFeeDiscounted;
	}

	public void setDeliveryFeeDiscounted(boolean deliveryFeeDiscounted) {
		this.deliveryFeeDiscounted = deliveryFeeDiscounted;
	}

	public Integer getUserOrderId() {
		return userOrderId;
	}

	public void setUserOrderId(Integer userOrderId) {
		this.userOrderId = userOrderId;
	}

	public Orders getOrder() {
		return order;
	}

	public void setOrder(Orders order) {
		this.order = order;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

}
