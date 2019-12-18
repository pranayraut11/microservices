package com.ecors.product.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecors.product.api.DTO.ProductDTO;

@RestController
@RequestMapping("product")
public class ProductController {

	@GetMapping("/{productID}")
	public ProductDTO getProduct(@PathVariable String productID) {
		ProductDTO productDTO = new ProductDTO();
		productDTO.setProductID(productID);
		productDTO.setProductName("TV");
		productDTO.setProductDescription("32inch");
		return productDTO;
	}

}
