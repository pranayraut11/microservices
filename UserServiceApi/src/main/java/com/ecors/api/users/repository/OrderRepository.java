package com.ecors.api.users.repository;

import org.springframework.data.repository.CrudRepository;

import com.ecors.api.users.entity.Orders;

public interface OrderRepository extends CrudRepository<Orders, Long> {

}
