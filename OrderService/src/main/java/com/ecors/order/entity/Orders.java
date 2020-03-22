package com.ecors.order.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Orders {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer orderId;

	private String user;

	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
	private Set<UserOrders> userOrder;

	private String address;

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddress() {
		return address;
	}

	public Set<UserOrders> getUserOrder() {
		return userOrder;
	}

	public void setUserOrder(Set<UserOrders> userOrder) {
		this.userOrder = userOrder;
	}

}
