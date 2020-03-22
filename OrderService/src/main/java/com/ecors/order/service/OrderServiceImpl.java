package com.ecors.order.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;

import com.ecors.core.client.ProductManagementClient;
import com.ecors.core.client.UserServiceClient;
import com.ecors.core.dto.OrderedProductDTO;
import com.ecors.core.dto.ProductDTO;
import com.ecors.core.enums.AddressType;
import com.ecors.core.enums.OrderStatus;
import com.ecors.core.exception.NotFoundException;
import com.ecors.core.ui.response.GenericResponse;
import com.ecors.order.entity.OrderDeliveryStatus;
import com.ecors.order.entity.Orders;
import com.ecors.order.entity.UserOrders;
import com.ecors.order.repository.OrderRepository;

public class OrderServiceImpl implements OrderService {

	@Autowired
	private ProductManagementClient productManagementClient;

	@Autowired
	private UserServiceClient userServiceClient;

	@Autowired
	private OrderRepository orderRepository;

	public static final boolean YES = true;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public String createOrder(List<Integer> order, String user) {

		// Retrieve user and delivery address
		// Get product details
		ResponseEntity<GenericResponse<List<com.ecors.core.dto.ProductDTO>>> productList = productManagementClient
				.getProductsByIDs(order);
		// Save order
		return orderRepository
				.save(saveOrderEntity(productList.getBody().getData().getResult(), user, getDeliveryAddress(user)))
				.getOrderId().toString();

	}

	private String getDeliveryAddress(String user) {

		return userServiceClient.getUserAddresses(AddressType.DELIVERY).getBody().getData().getResult().stream()
				.findFirst().get().getAddressId().toString();
	}

	private Orders saveOrderEntity(List<com.ecors.core.dto.ProductDTO> productList, String user, String addressid) {
		Assert.notEmpty(productList, "Product(s) not found");
		Orders order = new Orders();
		order.setAddress(addressid);
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
	public List<OrderedProductDTO> findAllOrdersByUser(String user) {

		// get all ordered products //call productmanagment microservice
		List<Orders> orders = orderRepository.findByUser(user).orElseThrow(() -> new NotFoundException("Orders"));
		List<Integer> orderProductds = getProductIds(orders);
		logger.debug("Ordered Product ids : {}", orderProductds);
		ResponseEntity<GenericResponse<List<ProductDTO>>> products = productManagementClient
				.getProductsByIDs(orderProductds);
		List<ProductDTO> fetchedproducts = products.getBody().getData().getResult();
		// get status of all orders

		return mapProductsWithOrdered(orders, fetchedproducts);
	}

	private List<Integer> getProductIds(List<Orders> orders) {
		List<Integer> ordersProductIds = new ArrayList<>();
		for (Orders order : orders) {

			ordersProductIds.addAll(order.getUserOrder().stream()
					.map(userOrder -> Integer.parseInt(userOrder.getProductId())).collect(Collectors.toList()));

		}
		return ordersProductIds;
	}

	private List<OrderedProductDTO> mapProductsWithOrdered(List<Orders> userOrders, List<ProductDTO> fetchedproducts) {
		Comparator<OrderDeliveryStatus> deliveryStatus = (o1, o2) -> o1.getDate().compareTo(o2.getDate());
		List<OrderedProductDTO> list = new ArrayList<>();
		for (Orders order : userOrders) {
			order.getUserOrder().forEach(userOrder -> {
				OrderedProductDTO orderedProductDTO = new OrderedProductDTO();
				fetchedproducts.forEach(fetchedProducs -> {
					if (fetchedProducs.getProductID().toString().equals(userOrder.getProductId())) {
						orderedProductDTO.setOrderId(order.getOrderId());
						orderedProductDTO.setProductID(fetchedProducs.getProductID().toString());
						orderedProductDTO.setProductName(fetchedProducs.getProductName());
						orderedProductDTO.setOrderStatus(userOrder.getOrderDeliveryStatus().stream()
								.sorted(deliveryStatus).findFirst().get().getDeliveryStatus());
						list.add(orderedProductDTO);
					}
				});
			});

		}
		return list;

	}

}
