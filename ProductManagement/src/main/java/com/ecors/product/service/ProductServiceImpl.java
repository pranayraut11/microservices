package com.ecors.product.service;

import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.ecors.core.utility.ModelMapperUtils;
import com.ecors.product.DTO.ProductDTO;
import com.ecors.product.entity.Product;
import com.ecors.product.entity.SubCategory;
import com.ecors.product.repository.ProductRepository;

@Service
public class ProductServiceImpl extends AbstractBaseService<Product, ProductDTO> implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Override
	public Optional<ProductDTO> get(int id, boolean active) {
		return super.get(id, ProductDTO.class, false, true).getDTO();
	}

	@Override
	public Map<Product, ProductDTO> getAllBySubCategory(SubCategory subCategory, boolean inActive, Pageable page) {
		Map<Product, ProductDTO> map = ModelMapperUtils
				.mapAllObejcts(productRepository.findBySubCategoryAndActive(subCategory, true).get(), ProductDTO.class);
		return map;
	}

}
