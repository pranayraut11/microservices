package com.ecors.api.users.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.ecors.api.users.entity.Address;
import com.ecors.api.users.entity.User;
import com.ecors.api.users.enums.AddressType;

public interface AddressRepository extends CrudRepository<Address, Long> {

	Optional<Address> findByUserAndDeliveryAddress(User user, boolean isDeliveryAddress);

	Optional<List<Address>> findByUser(User user);

	Optional<Address> findByAddressIdAndUser(Long id,User user);
	
	Optional<List<Address>> findByUserAndType(User user, AddressType type);
}

