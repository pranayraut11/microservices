package com.ecors.product.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ecors.product.entity.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {

	@Query("SELECT p FROM Product p WHERE p.active=:inActive AND p.subCategory=:subCategory ")
	Optional<List<Product>> findBySubCategoryAndActive(String subCategory, boolean inActive);

	Optional<List<Product>> findByProductIDIn(List<Integer> ids);

	Optional<Product> findByProductIDAndSellerId(String productId, String sellerId);

}
