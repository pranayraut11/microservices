package com.ecors.api.users.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.ecors.api.users.entity.Address;
import com.ecors.api.users.entity.User;
import com.ecors.core.enums.AddressType;

public interface AddressRepository extends CrudRepository<Address, Integer> {

	Optional<Address> findByUserAndDeliveryAddress(User user, boolean isDeliveryAddress);

	Optional<List<Address>> findByUser(User user);

	Optional<Address> findByAddressIdAndUser(Integer id,User user);
	
	Optional<List<Address>> findByUserAndType(User user, AddressType type);
}

