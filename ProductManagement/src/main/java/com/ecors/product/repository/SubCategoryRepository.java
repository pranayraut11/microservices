package com.ecors.product.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.ecors.product.entity.SubCategory;

public interface SubCategoryRepository extends CrudRepository<SubCategory, Integer> {

	Optional<List<SubCategory>> findByActive(boolean active);

}
