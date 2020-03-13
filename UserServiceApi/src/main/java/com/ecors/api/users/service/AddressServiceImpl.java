package com.ecors.api.users.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.ecors.api.users.DTO.AddressDTO;
import com.ecors.api.users.entity.Address;
import com.ecors.api.users.entity.User;
import com.ecors.api.users.enums.AddressType;
import com.ecors.api.users.repository.AddressRepository;
import com.ecors.core.exception.NotFoundException;
import com.ecors.core.utility.ModelMapperUtils;

@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	private AddressRepository addressRepository;
	@Autowired
	private UserService userService;
	@Autowired
	private AuthenticationService authenticationService;

	@Override
	public void save(AddressDTO addressDTo, String uuid) {
		User user = authenticationService.getUserFromUUID(uuid);
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
	public void updateDeliveryAddress(Integer addressID, String uuid) {
		User user = authenticationService.getUserFromUUID(uuid);
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
	public List<AddressDTO> get(AddressType type, String userID) {
		switch (type) {
		case DELIVERY:
			List<AddressDTO> addressList = new ArrayList<AddressDTO>();
			addressList.add(getDeliveryAddressByUser(userID));
			return addressList;
		case ALL:
			return getAllByUserId(userService.getUser(userID));
		default:
			break;
		}
		return null;
	}

	@Override
	public AddressDTO getDeliveryAddressByUser(String uuid) {
		Optional<Address> optionalAddress = addressRepository
				.findByUserAndDeliveryAddress(authenticationService.getUserFromUUID(uuid), true);
		if (optionalAddress.isPresent()) {
			return ModelMapperUtils.map(optionalAddress.get(), AddressDTO.class);
		}
		throw new NotFoundException("Delivery address");
	}

}
