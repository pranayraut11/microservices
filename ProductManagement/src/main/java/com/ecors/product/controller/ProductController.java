package com.ecors.product.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecors.core.ui.response.GenericResponse;
import com.ecors.core.ui.response.Response;
import com.ecors.product.DTO.OrderSummary;
import com.ecors.product.DTO.ProductDTO;
import com.ecors.product.service.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@RestController
@RequestMapping("products")
public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping("/{id}")
	public ResponseEntity<GenericResponse<ProductDTO>> getProduct(@PathVariable int id)
			throws JsonMappingException, JsonProcessingException {
		Response<ProductDTO> response = new Response<>();
		response.setResult(productService.get(id, true));
		GenericResponse<ProductDTO> reponse = new GenericResponse<ProductDTO>(response, "Product retrived successfully",
				true);
		return ResponseEntity.status(HttpStatus.OK).body(reponse);
	}

	@GetMapping("/{id}/ordersummary")
	public ResponseEntity<GenericResponse<OrderSummary>> getProductOrderSummary(@PathVariable int id)
			throws JsonMappingException, JsonProcessingException {
		Response<OrderSummary> response = new Response<>();
		List<Integer> ids = Arrays.asList(id);
		response.setResult(productService.getProductOrderSummary(ids));
		GenericResponse<OrderSummary> reponse = new GenericResponse<OrderSummary>(response,
				"Product retrived successfully", true);
		return ResponseEntity.status(HttpStatus.OK).body(reponse);
	}
	
	@GetMapping()
	public ResponseEntity<GenericResponse<List<ProductDTO>>> getProductsById(@RequestParam List<Integer> id)
			throws JsonMappingException, JsonProcessingException {
		Response<List<ProductDTO>> response = new Response<>();
		response.setResult(productService.getProductsByIds(id));
		GenericResponse<List<ProductDTO>> reponse = new GenericResponse<List<ProductDTO>>(response, "Product retrived successfully",
				true);
		return ResponseEntity.status(HttpStatus.OK).body(reponse);
	}

}
