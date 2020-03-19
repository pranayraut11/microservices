package com.ecors.api.users.service;

import java.util.List;

import com.ecors.api.users.entity.User;
import com.ecors.core.dto.OrderedProductDTO;

public interface OrderService {

	String createOrder(List<Integer> productIds, User user);

	List<OrderedProductDTO> findAllOrdersByUser(User user);
	
	
}
