package com.ecors.product.controller;

import java.util.ArrayList;
import java.util.Collection;
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
		response.setResult(productService.get(productID, true).get());
		GenericResponse<ProductDTO> reponse = new GenericResponse<ProductDTO>(response, "Offers retrived successfully",
				true);
		return ResponseEntity.status(HttpStatus.OK).body(reponse);
	}

	@GetMapping()
	public ResponseEntity<GenericResponse<Collection<ProductDTO>>> getProductList(@PathVariable String category,
			@RequestParam int limit, @RequestParam int offset) {

		Response<Collection<ProductDTO>> response = new Response<>();
		response.setResult(productService.getAllBySubCategory(category, true).getDTOList());
		GenericResponse<Collection<ProductDTO>> reponse = new GenericResponse<Collection<ProductDTO>>(response,
				"Offers retrived successfully", true);
		return ResponseEntity.status(HttpStatus.OK).body(reponse);
	}

}
