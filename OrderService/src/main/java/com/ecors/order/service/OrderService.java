package com.ecors.order.service;

import java.util.List;

import com.ecors.core.dto.OrderedProductDTO;

public interface OrderService {

	String createOrder(List<Integer> productIds, String user);

	List<OrderedProductDTO> findAllOrdersByUser(String user);

}
