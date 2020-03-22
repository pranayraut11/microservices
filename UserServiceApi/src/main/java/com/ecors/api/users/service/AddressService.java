package com.ecors.api.users.service;

import java.util.List;

import com.ecors.api.users.entity.User;
import com.ecors.core.dto.AddressDTO;
import com.ecors.core.enums.AddressType;

public interface AddressService {

	public void save(AddressDTO addressDTo, User userId);

	public void delet(Integer addressID, User userId);

	public void updateDeliveryAddress(Integer addressID, User userID);

	public AddressDTO get(Integer id);

	public List<AddressDTO> get(AddressType type, User userID);

	public List<AddressDTO> getAllByUserId(User userId);

	public AddressDTO getDeliveryAddressByUser(User userId);

	public void updateAddress(AddressDTO addressDTo, User user);

	List<AddressDTO> getAddressByUserAddressType(User userId, AddressType type);

}
