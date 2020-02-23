package com.ecors.api.users.service;

import java.util.List;

import com.ecors.api.users.DTO.AddressDTO;
import com.ecors.api.users.entity.User;

public interface AddressService {

	public void save(AddressDTO addressDTo);
	
	public void updateDeliveryAddress(String userID,Long addressID);

	public AddressDTO get(Long id);
	
	public List<AddressDTO> getAllByUserId(User userId);
	
	public AddressDTO getDeliveryAddressByUser(User userId);
	
}
