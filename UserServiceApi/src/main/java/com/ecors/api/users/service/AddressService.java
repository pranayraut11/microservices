package com.ecors.api.users.service;

import java.util.List;

import com.ecors.api.users.DTO.AddressDTO;
import com.ecors.api.users.entity.User;
import com.ecors.api.users.enums.AddressType;

public interface AddressService {

	public void save(AddressDTO addressDTo, String userId);

	public void updateDeliveryAddress(Long addressID, String userID, AddressType type);

	public AddressDTO get(Long id);

	public List<AddressDTO> get(AddressType type, String userID);

	public List<AddressDTO> getAllByUserId(User userId);

	public AddressDTO getDeliveryAddressByUser(String userId);

	List<AddressDTO> getAddressByUserAddressType(User userId, AddressType type);

}
