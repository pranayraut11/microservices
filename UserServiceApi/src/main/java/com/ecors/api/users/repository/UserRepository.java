package com.ecors.api.users.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.ecors.api.users.entity.User;

public interface UserRepository extends CrudRepository<User, Long> {

	Optional<User> findUserByUsername(String username);

	Optional<User> findUserByUserId(String userID);

}
