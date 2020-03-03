package com.ecors.api.users.repository;

import org.springframework.data.repository.CrudRepository;

import com.ecors.api.users.entity.Order;

public interface OrderRepository extends CrudRepository<Order, Long> {

}
