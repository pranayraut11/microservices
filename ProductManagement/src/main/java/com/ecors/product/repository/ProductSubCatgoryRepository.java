package com.ecors.product.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.ecors.product.entity.ProductSubCategory;

public interface ProductSubCatgoryRepository extends CrudRepository<ProductSubCategory, Integer> {

	Optional<List<ProductSubCategory>> findBySubCategory(String subCategory);
}
