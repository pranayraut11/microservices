package com.ecors.category.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecors.category.service.OfferService;
import com.ecors.core.dto.OfferDTO;
import com.ecors.core.ui.response.GenericResponse;
import com.ecors.core.ui.response.Response;

@RestController
@RequestMapping("offers")
public class OfferController {

	@Autowired
	private OfferService offerService;

	@GetMapping()
	public ResponseEntity<GenericResponse<Collection<OfferDTO>>> listOffers(@RequestParam int limit,
			@RequestParam int offset) {

		Response<Collection<OfferDTO>> response = new Response<>();
		response.setResult(offerService.getAll(true));
		GenericResponse<Collection<OfferDTO>> reponse = new GenericResponse<Collection<OfferDTO>>(response,
				"Offers retrived successfully", true);
		return ResponseEntity.status(HttpStatus.OK).body(reponse);

	}

}
