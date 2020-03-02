package com.ecors.api.users.entity;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.ecors.api.users.enums.OrderStatus;

@Entity
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long orderId;

	@ManyToOne
	@JoinColumn(name = "id")
	private User user;

	@OneToMany(mappedBy = "order")
	private Set<UserOrder> userOrder;
	
	@OneToOne
	@JoinColumn(name = "addressId")
	private Address address;

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<UserOrder> getUserOrder() {
		return userOrder;
	}

	public void setUserOrder(Set<UserOrder> userOrder) {
		this.userOrder = userOrder;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public LocalDateTime getExpectedDeliveryDate() {
		return expectedDeliveryDate;
	}

	public void setExpectedDeliveryDate(LocalDateTime expectedDeliveryDate) {
		this.expectedDeliveryDate = expectedDeliveryDate;
	}

	private Long productId;

	private OrderStatus status;

	private LocalDateTime expectedDeliveryDate;

}
