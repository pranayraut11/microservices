package com.ecors.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecors.core.ui.response.GenericResponse;
import com.ecors.core.ui.response.Response;
import com.ecors.product.DTO.ProductDTO;
import com.ecors.product.service.ProductService;

@RestController
@RequestMapping("product")
public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping("/{productID}")
	public ResponseEntity<GenericResponse<ProductDTO>> getProduct(@PathVariable int productID) {
		
		Response<ProductDTO> response = new Response<>();
		response.setResult(productService.get(productID, true));
		GenericResponse<ProductDTO> reponse = new GenericResponse<ProductDTO>(response, "Offers retrived successfully",
				true);
		return ResponseEntity.status(HttpStatus.OK).body(reponse);
	}

}
