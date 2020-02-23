package com.ecors.api.users.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.ecors.api.users.DTO.AddressDTO;
import com.ecors.api.users.entity.Address;
import com.ecors.api.users.entity.User;
import com.ecors.api.users.repository.AddressRepository;
import com.ecors.core.exception.NotFoundException;
import com.ecors.core.utility.ModelMapperUtils;

@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	private AddressRepository addressRepository;
	@Autowired
	private UserService userService;

	@Override
	public void save(AddressDTO addressDTo) {
		addressRepository.save(ModelMapperUtils.map(addressDTo, Address.class));
	}

	@Override
	public AddressDTO get(Long id) {
		Optional<Address> optionalAddress = addressRepository.findById(id);
		if (optionalAddress.isPresent()) {
			return ModelMapperUtils.map(optionalAddress.get(), AddressDTO.class);
		}
		throw new NotFoundException("Address");
	}

	@Override
	public List<AddressDTO> getAllByUserId(User userId) {
		return ModelMapperUtils.mapAll(getAddress(userId), AddressDTO.class);
	}

	private List<Address> getAddress(User user) {
		Optional<List<Address>> optAddress = addressRepository.findByUser(user);
		if (optAddress.isPresent()) {
			return optAddress.get();
		}
		throw new NotFoundException("Addresses");
	}

	@Override
	public AddressDTO getDeliveryAddressByUser(User userId) {
		Optional<Address> optionalAddress = addressRepository.findByUserAndDeliveryAddress(userId, true);
		if (optionalAddress.isPresent()) {
			return ModelMapperUtils.map(optionalAddress.get(), AddressDTO.class);
		}
		throw new NotFoundException("Address");
	}

	@Override
	public void updateDeliveryAddress(String userID, Long addressID) {
		Optional<Address> optAddress = addressRepository.findByAddressIdAndUser(addressID, userService.getUser(userID));
		if (optAddress.isPresent()) {
			Address address = optAddress.get();
			address.setDeliveryAddress(true);
			addressRepository.save(address);
			return;
		}
		throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Operation not allowed");

	}

}
