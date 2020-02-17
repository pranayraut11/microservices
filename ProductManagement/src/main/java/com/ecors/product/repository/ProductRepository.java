package com.ecors.product.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ecors.product.entity.Product;
import com.ecors.product.entity.SubCategory;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer>{

	@Query("SELECT p FROM Product p,SubCategory s WHERE p.active=:inActive AND s=:subCategory ")
	Optional<List<Product>> findBySubCategoryAndActive(SubCategory subCategory,boolean inActive);
	
}
