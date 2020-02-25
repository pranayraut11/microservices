package com.ecors.api.users.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecors.api.users.DTO.AddressDTO;
import com.ecors.api.users.service.AddressService;
import com.ecors.core.ui.response.GenericResponse;

@RestController
@RequestMapping("addresses")
public class AddressController {

	@Autowired
	private AddressService addressService;

	

	@PostMapping()
	public ResponseEntity<GenericResponse<Void>> saveAddress(@RequestBody AddressDTO address) {
		GenericResponse<Void> genericResponse = new GenericResponse<Void>(null, "Address saved successfullty", true);
		addressService.save(address);
		return ResponseEntity.status(HttpStatus.CREATED).body(genericResponse);
	}

	@PatchMapping("{addressId}/change")
	public ResponseEntity<GenericResponse<Void>> updateDeliveryAddress(@RequestBody Long addressId,
			HttpServletRequest request) {
		request.getHeader("token");
		GenericResponse<Void> genericResponse = new GenericResponse<Void>(null, "Address saved successfullty", true);
		addressService.updateDeliveryAddress("asd", addressId);
		return ResponseEntity.status(HttpStatus.CREATED).body(genericResponse);
	}

}
