package com.ecors.api.users.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.ecors.api.users.entity.Address;
import com.ecors.api.users.entity.User;
import com.ecors.api.users.repository.AddressRepository;
import com.ecors.core.dto.AddressDTO;
import com.ecors.core.enums.AddressType;
import com.ecors.core.exception.NotFoundException;
import com.ecors.core.utility.ModelMapperUtils;

@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	private AddressRepository addressRepository;

	@Override
	public void save(AddressDTO addressDTo, User user) {
		Optional<List<Address>> optAddress = addressRepository.findByUser(user);
		if (!optAddress.isPresent()) {
			addressDTo.setDeliveryAddress(true);
		} else {
			addressDTo.setDeliveryAddress(false);
		}
		Address addressResponse = ModelMapperUtils.map(addressDTo, Address.class);
		addressResponse.setUser(user);
		addressRepository.save(addressResponse);
	}

	@Override
	public void updateAddress(AddressDTO addressDTo, User user) {
		Address address = getAddressbyId(addressDTo.getAddressId(), user);
		ModelMapperUtils.map(addressDTo, address);
		addressRepository.save(address);
	}

	private Address getAddressbyId(Integer id, User user) {
		Optional<Address> addressOpt = addressRepository.findByAddressIdAndUser(id, user);
		Address address = addressOpt.orElseThrow(() -> new NotFoundException("Address"));
		return address;

	}

	@Override
	public AddressDTO get(Integer id) {
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
	public List<AddressDTO> getAddressByUserAddressType(User userId, AddressType type) {
		Optional<List<Address>> optionalAddress = addressRepository.findByUserAndType(userId, type);
		if (optionalAddress.isPresent()) {
			return ModelMapperUtils.mapAll(optionalAddress.get(), AddressDTO.class);
		}
		throw new NotFoundException("Address");
	}

	@Override
	public void updateDeliveryAddress(Integer addressID, User user) {
		Optional<Address> optAddress = addressRepository.findByAddressIdAndUser(addressID, user);
		changeDeliveryAddress(user);
		if (optAddress.isPresent()) {
			Address address = optAddress.get();
			address.setDeliveryAddress(true);
			addressRepository.save(address);
			return;
		}
		throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Operation not allowed");

	}

	private void changeDeliveryAddress(User user) {
		Optional<Address> optionalAddress = addressRepository.findByUserAndDeliveryAddress(user, true);
		if (optionalAddress.isPresent()) {
			Address deliveryAddress = optionalAddress.get();
			deliveryAddress.setDeliveryAddress(false);
			addressRepository.save(deliveryAddress);
		}
	}

	@Override
	public List<AddressDTO> get(AddressType type, User user) {
		switch (type) {
		case DELIVERY:
			List<AddressDTO> addressList = new ArrayList<AddressDTO>();
			addressList.add(getDeliveryAddressByUser(user));
			return addressList;
		case ALL:
			return getAllByUserId(user);
		default:
			break;
		}
		return null;
	}

	@Override
	public AddressDTO getDeliveryAddressByUser(User user) {
		Optional<Address> optionalAddress = addressRepository.findByUserAndDeliveryAddress(user, true);
		if (optionalAddress.isPresent()) {
			return ModelMapperUtils.map(optionalAddress.get(), AddressDTO.class);
		}
		throw new NotFoundException("Delivery address");
	}

	@Override
	public void delet(Integer addressID, User user) {
		addressRepository.delete(getAddressbyId(addressID, user));
	}

}
