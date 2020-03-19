package com.ecors.api.users.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.ecors.api.users.entity.LoginDetails;
import com.ecors.api.users.entity.User;

public interface LoginDetailsRepository extends CrudRepository<LoginDetails, Integer> {

	Optional<LoginDetails> findByUuid(String uuid);

	Optional<LoginDetails> findByUuidAndUser(String uuid, User user);
}
