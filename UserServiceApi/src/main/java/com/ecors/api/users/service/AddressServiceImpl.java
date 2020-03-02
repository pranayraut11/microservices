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

	@Override
	public void save(AddressDTO addressDTo, String userID) {
		User user = userService.getUser(userID);
		List<Address> address = getAddress(user);
		if (address == null) {
			addressDTo.setType(AddressType.DELIVERY);
		}
		Address addressResponse = ModelMapperUtils.map(addressDTo, Address.class);
		addressResponse.setUser(user);
		addressRepository.save(addressResponse);
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
	public void updateDeliveryAddress(Long addressID, String userID,AddressType type) {
		Optional<Address> optAddress = addressRepository.findByAddressIdAndUser(addressID, userService.getUser(userID));
		if (optAddress.isPresent()) {
			Address address = optAddress.get();
			address.setDeliveryAddress(true);
			address.setType(type);
			addressRepository.save(address);
			return;
		}
		throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Operation not allowed");

	}

	@Override
	public List<AddressDTO> get(AddressType type, String userID) {
		switch (type) {
		case DELIVERY:
			ArrayList<AddressDTO> addressList = new ArrayList<AddressDTO>();
			addressList.add(getDeliveryAddressByUser(userService.getUser(userID)));
			return addressList;
		case ALL:
			return getAllByUserId(userService.getUser(userID));
		default:
			break;
		}
		return null;
	}

}
