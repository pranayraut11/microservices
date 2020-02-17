package com.ecors.product.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.ecors.product.entity.Product;
import com.ecors.product.entity.ProductSubCategory;
import com.ecors.product.entity.SubCategory;

public interface ProductSubCatgoryRepository extends CrudRepository<ProductSubCategory, Integer> {

	Optional<List<Product>> findBySubCategory(SubCategory subCategory);
}
