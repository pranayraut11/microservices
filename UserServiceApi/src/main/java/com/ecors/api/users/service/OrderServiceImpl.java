package com.ecors.api.users.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;

import com.ecors.api.users.entity.Address;
import com.ecors.api.users.entity.OrderDeliveryStatus;
import com.ecors.api.users.entity.Orders;
import com.ecors.api.users.entity.User;
import com.ecors.api.users.entity.UserOrders;
import com.ecors.api.users.repository.OrderRepository;
import com.ecors.api.users.service.client.ProductManagementClient;
import com.ecors.core.dto.OrderedProductDTO;
import com.ecors.core.dto.ProductDTO;
import com.ecors.core.enums.OrderStatus;
import com.ecors.core.ui.response.GenericResponse;

public class OrderServiceImpl implements OrderService {

	@Autowired
	private ProductManagementClient productManagementClient;

	@Autowired
	private OrderRepository orderRepository;

	public static final boolean YES = true;

	@Override
	public String createOrder(List<Integer> order, User user) {

		// Retrieve user and delivery address
		// Get product details
		ResponseEntity<GenericResponse<List<com.ecors.core.dto.ProductDTO>>> productList = productManagementClient
				.getProductsByIDs(order);
		// Save order
		return orderRepository.save(saveOrderEntity(productList.getBody().getData().getResult(), user,
				getDeliveryAddress(user.getAddresses()))).getOrderId().toString();

	}

	private Address getDeliveryAddress(Set<Address> addresses) {
		Assert.notEmpty(addresses, "Please save atleast one address");
		List<Address> deliveryAddress = addresses.stream().filter(addres -> addres.isDeliveryAddress() == YES)
				.collect(Collectors.toList());
		Assert.notEmpty(deliveryAddress, "Delivery address not available");
		return deliveryAddress.stream().findFirst().get();
	}

	private Orders saveOrderEntity(List<com.ecors.core.dto.ProductDTO> productList, User user, Address address) {
		Assert.notEmpty(productList, "Product(s) not found");
		Orders order = new Orders();
		order.setAddress(address);
		order.setUser(user);
		Set<UserOrders> userOrders = new HashSet<>();
		productList.forEach(product -> {
			userOrders.add(saveUserOrders(product.getProductID(), product.isDeliveryFeeDiscounted(),
					product.getDeliveryDate(), order));
		});

		order.setUserOrder(userOrders);
		return order;
	}

	private UserOrders saveUserOrders(Integer productId, boolean deliveryFeeDiscounted, LocalDateTime deliveryDate,
			Orders orders) {
		UserOrders userOrders = new UserOrders();
		userOrders.setDeliveryFeeDiscounted(deliveryFeeDiscounted);
		userOrders.setProductId(productId.toString());
		userOrders.setDeliveryDate(deliveryDate);
		Set<OrderDeliveryStatus> orderDeliveryStatus = new HashSet<>();
		orderDeliveryStatus.add(saveOrderDeliveryStatus(userOrders));
		userOrders.setOrderDeliveryStatus(orderDeliveryStatus);
		userOrders.setOrder(orders);
		return userOrders;
	}

	private OrderDeliveryStatus saveOrderDeliveryStatus(UserOrders userOrders) {
		OrderDeliveryStatus orderDeliveryStatus = new OrderDeliveryStatus();
		orderDeliveryStatus.setDeliveryStatus(OrderStatus.CREATED);
		orderDeliveryStatus.setDate(LocalDateTime.now());
		orderDeliveryStatus.setUserOrders(userOrders);
		return orderDeliveryStatus;
	}

	@Override
	public List<OrderedProductDTO> findAllOrdersByUser(User user) {
		// get all ordered products //call productmanagment microservice
		List<Integer> productIds = new ArrayList<>();

		for (Orders order : user.getOrders()) {
			productIds.addAll(order.getUserOrder().stream().map(userOrder -> Integer.parseInt(userOrder.getProductId()))
					.collect(Collectors.toList()));
		}

		ResponseEntity<GenericResponse<List<ProductDTO>>> products = productManagementClient
				.getProductsByIDs(productIds);
		
		// get status of all orders

		return null;
	}

}
