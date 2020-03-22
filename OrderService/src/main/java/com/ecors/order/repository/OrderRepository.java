package com.ecors.order.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.ecors.order.entity.Orders;

public interface OrderRepository extends CrudRepository<Orders, Long> {

	Optional<List<Orders>> findByUser(String userId);
}
