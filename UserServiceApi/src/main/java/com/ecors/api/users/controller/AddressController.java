package com.ecors.api.users.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecors.api.users.DTO.AddressDTO;
import com.ecors.api.users.enums.AddressType;
import com.ecors.api.users.service.AddressService;
import com.ecors.api.users.service.AuthenticationService;
import com.ecors.api.users.ui.request.UpdateDeliveryAddress;
import com.ecors.core.ui.response.GenericResponse;
import com.ecors.core.ui.response.Response;

@RestController
@RequestMapping("addresses")
public class AddressController {

	@Autowired
	private AddressService addressService;

	@Autowired
	private AuthenticationService authenticationService;

	@PostMapping
	public ResponseEntity<GenericResponse<Void>> saveAddress(@RequestBody AddressDTO address,
			HttpServletRequest request) {
		GenericResponse<Void> genericResponse = new GenericResponse<Void>(null, "Address saved successfullty", true);
		addressService.save(address, authenticationService.getUser(request));
		return ResponseEntity.status(HttpStatus.CREATED).body(genericResponse);
	}

	@PatchMapping("{addressId}/change")
	public ResponseEntity<GenericResponse<Void>> updateDeliveryAddress(@PathVariable Integer addressId,
			@RequestBody UpdateDeliveryAddress type, HttpServletRequest request) {
		GenericResponse<Void> genericResponse = new GenericResponse<Void>(null, "Address saved successfullty", true);
		addressService.updateDeliveryAddress(addressId, authenticationService.getUser(request));
		return ResponseEntity.status(HttpStatus.CREATED).body(genericResponse);
	}

	@GetMapping
	public ResponseEntity<GenericResponse<List<AddressDTO>>> getUserAddresses(@RequestParam AddressType type,
			HttpServletRequest request) {
		Response<List<AddressDTO>> result = new Response<List<AddressDTO>>();
		result.setResult(addressService.get(type, authenticationService.getUser(request)));
		GenericResponse<List<AddressDTO>> response = new GenericResponse<List<AddressDTO>>(result,
				"Address retrived successfully", true);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	
}
