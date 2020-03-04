package com.ecors.api.users.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Orders {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer orderId;

	@ManyToOne
	@JoinColumn(name = "id")
	private User user;

	@OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
	private Set<UserOrders> userOrder;

	@OneToOne
	@JoinColumn(name = "addressId")
	private Address address;

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<UserOrders> getUserOrder() {
		return userOrder;
	}

	public void setUserOrder(Set<UserOrders> userOrder) {
		this.userOrder = userOrder;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

}
