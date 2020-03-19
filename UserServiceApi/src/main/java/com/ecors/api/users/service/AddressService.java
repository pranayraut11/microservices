package com.ecors.api.users.service;

import java.util.List;

import com.ecors.api.users.DTO.AddressDTO;
import com.ecors.api.users.entity.User;
import com.ecors.api.users.enums.AddressType;

public interface AddressService {

	public void save(AddressDTO addressDTo, User userId);

	public void updateDeliveryAddress(Integer addressID, User userID);

	public AddressDTO get(Integer id);

	public List<AddressDTO> get(AddressType type, User userID);

	public List<AddressDTO> getAllByUserId(User userId);

	public AddressDTO getDeliveryAddressByUser(User userId);

	List<AddressDTO> getAddressByUserAddressType(User userId, AddressType type);

}
