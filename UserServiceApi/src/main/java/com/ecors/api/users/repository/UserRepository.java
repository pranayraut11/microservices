package com.ecors.api.users.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.ecors.api.users.entity.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity, Long> {

	Optional<UserEntity> findUserByEmailID(String username);

	Optional<UserEntity> findUserByUserId(String userID);

}
