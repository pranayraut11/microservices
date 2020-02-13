package com.ecors.product.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ecors.core.dto.ServiceResponse;
import com.ecors.product.DTO.ProductDTO;
import com.ecors.product.entity.Product;

@Service
public class ProductServiceImpl extends AbstractBaseService<Product, ProductDTO> implements ProductService {

	@Override
	public Optional<ProductDTO> get(int id, boolean active) {
		return super.get(id, ProductDTO.class, false, true).getDTO();
	}

	@Override
	public ServiceResponse<Product, ProductDTO> getAllBySubCategory(String subCategory, boolean active) {
		return super.getAll(ProductDTO.class, false, true);
	}

}
