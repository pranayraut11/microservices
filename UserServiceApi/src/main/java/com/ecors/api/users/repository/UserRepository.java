package com.ecors.api.users.repository;

import org.springframework.data.repository.CrudRepository;

import com.ecors.api.users.entity.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity, Long> {

	UserEntity findUserByEmailID(String username);

	UserEntity findUserByUserId(String userID);

}
