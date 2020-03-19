package com.ecors.api.users.controller;

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

import com.ecors.api.users.service.AuthenticationService;
import com.ecors.api.users.service.OrderService;
import com.ecors.core.dto.OrderedProductDTO;
import com.ecors.core.ui.response.GenericResponse;
import com.ecors.core.ui.response.Response;

@RestController
@RequestMapping("orders")
public class OrderController {

	@Autowired
	private OrderService userService;

	@Autowired
	private AuthenticationService authenticationService;

	@PostMapping()
	public ResponseEntity<GenericResponse<String>> buyProducts(@RequestBody List<Integer> order,
			HttpServletRequest request) {
		Response<String> result = new Response<>();
		result.setResult(userService.createOrder(order, authenticationService.getUser(request)));
		GenericResponse<String> genericResponse = new GenericResponse<String>(result, "Order placed successfully",
				true);
		return ResponseEntity.status(HttpStatus.CREATED).body(genericResponse);

	}

	@GetMapping
	public ResponseEntity<GenericResponse<List<OrderedProductDTO>>> getAllOrdersByUser(HttpServletRequest request) {
		Response<List<OrderedProductDTO>> result = new Response<>();
		result.setResult(userService.findAllOrdersByUser(authenticationService.getUser(request)));
		GenericResponse<List<OrderedProductDTO>> genericResponse = new GenericResponse<List<OrderedProductDTO>>(result,
				"Order(s) retrieved successfully", true);
		return ResponseEntity.status(HttpStatus.CREATED).body(genericResponse);
	}

}
