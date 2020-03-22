package com.ecors.category.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.ecors.category.entity.OfferSubCategory;
import com.ecors.category.entity.SubCategory;

public interface SubCategoryRepository extends CrudRepository<SubCategory, Integer> {

	Optional<List<SubCategory>> findByActive(boolean active);

	Optional<List<SubCategory>> findByActiveAndOfferSubcategories(boolean active, OfferSubCategory offerSubcategory);
}
