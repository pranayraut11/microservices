package com.ecors.api.users.service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ecors.api.users.DTO.ProductDTO;

@FeignClient(name = "productmanagement")
public interface ProductManagementClient {

	@GetMapping("/product/{productID}")
	public ProductDTO getProductByID(@PathVariable String productID);
}
