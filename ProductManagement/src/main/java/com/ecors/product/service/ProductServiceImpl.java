package com.ecors.product.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ecors.core.utility.ModelMapperUtils;
import com.ecors.product.DTO.ProductDTO;
import com.ecors.product.entity.SubCategory;
import com.ecors.product.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Override
	public ProductDTO get(int id, boolean active) {
		return ModelMapperUtils.map(productRepository.findById(id).get(), ProductDTO.class);
	}

	@Override
	public List<ProductDTO> getAllBySubCategory(SubCategory subCategory, boolean inActive, Pageable page) {
		return ModelMapperUtils
				.mapAll(productRepository.findBySubCategoryAndActive(subCategory, true).get(), ProductDTO.class).get();

	}

}
