package com.ecors.order.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecors.core.client.UserServiceClient;
import com.ecors.core.dto.OrderedProductDTO;
import com.ecors.core.ui.response.GenericResponse;
import com.ecors.core.ui.response.Response;
import com.ecors.order.service.OrderService;

@RestController
@RequestMapping("orders")
public class OrderController {

	@Autowired
	private OrderService userService;

	@Autowired
	private UserServiceClient userServiceClient;

	@PostMapping()
	public ResponseEntity<GenericResponse<String>> buyProducts(@RequestBody List<Integer> order,
			HttpServletRequest request) throws Exception {
		Response<String> result = new Response<>();
		result.setResult(userService.createOrder(order,
				userServiceClient.getUser(request).getBody().getData().getResult().getUserID()));
		GenericResponse<String> genericResponse = new GenericResponse<String>(result, "Order placed successfully",
				true);
		return ResponseEntity.status(HttpStatus.CREATED).body(genericResponse);

	}

	@GetMapping
	public ResponseEntity<GenericResponse<List<OrderedProductDTO>>> getAllOrdersByUser(HttpServletRequest request)
			throws Exception {
		Response<List<OrderedProductDTO>> result = new Response<>();
		result.setResult(userService
				.findAllOrdersByUser(userServiceClient.getUser(request).getBody().getData().getResult().getUserID()));
		GenericResponse<List<OrderedProductDTO>> genericResponse = new GenericResponse<List<OrderedProductDTO>>(result,
				"Order(s) retrieved successfully", true);
		return ResponseEntity.status(HttpStatus.CREATED).body(genericResponse);
	}

}
