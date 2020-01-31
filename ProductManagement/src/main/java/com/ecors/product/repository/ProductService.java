package com.ecors.product.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ecors.product.entity.Product;

@Repository
public interface ProductService extends CrudRepository<Product, Integer>{

}
