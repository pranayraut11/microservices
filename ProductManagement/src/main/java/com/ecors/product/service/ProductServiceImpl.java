package com.ecors.product.service;

import org.springframework.stereotype.Service;

import com.ecors.product.DTO.ProductDTO;
import com.ecors.product.entity.Product;

@Service
public class ProductServiceImpl extends AbstractBaseService<Product,ProductDTO> implements ProductService {

	@Override
	public ProductDTO getAllBySubCategory(String subCategory, boolean active) {
		super.get(0,false,true);
		return null;
	}

}
