package com.ecors.api.users.service.client;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.ecors.core.dto.ProductDTO;
import com.ecors.core.ui.response.GenericResponse;
import com.ecors.core.ui.response.Response;

import feign.FeignException;
import feign.hystrix.FallbackFactory;

@FeignClient(name = "productmanagement", fallbackFactory = ProductManagmentFallbackService.class)
public interface ProductManagementClient {

	@GetMapping("/products/{productID}")
	public ProductDTO getProductByID(@PathVariable String productID);

	@GetMapping("/products")
	public ResponseEntity<GenericResponse<List<ProductDTO>>> getProductsByIDs(@RequestParam List<Integer> id);

}

@Component
class ProductManagmentFallbackService implements FallbackFactory<ProductManagementClient> {

	@Override
	public ProductManagementClient create(Throwable cause) {
		// TODO Auto-generated method stub
		return new ProductManagmentService(cause);
	}

}

class ProductManagmentService implements ProductManagementClient {

	private final Throwable cause;
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	ProductManagmentService(Throwable cause) {
		this.cause = cause;
	}

	@Override
	public ProductDTO getProductByID(String productID) {

		if ((cause instanceof FeignException) && ((FeignException) cause).status() == 404) {
			logger.error(cause.getLocalizedMessage());
		} else {
			logger.error(cause.getLocalizedMessage());
		}
		return new ProductDTO();
	}

	@Override
	public ResponseEntity<GenericResponse<List<ProductDTO>>> getProductsByIDs(List<Integer> productIds) {
		if ((cause instanceof FeignException) && ((FeignException) cause).status() == 404) {
			logger.error(cause.getLocalizedMessage());
		} else {
			logger.error(cause.getLocalizedMessage());
		}
		Response<List<ProductDTO>> result=new Response<>();
		GenericResponse<List<ProductDTO>> response=new GenericResponse<>(null, "No data found", true);
		response.setData(result);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

}
