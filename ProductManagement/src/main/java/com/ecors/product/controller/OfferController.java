package com.ecors.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecors.core.ui.response.GenericResponse;
import com.ecors.core.ui.response.Response;
import com.ecors.product.DTO.OfferDTO;
import com.ecors.product.service.OfferService;

@RestController
@RequestMapping("offers")
public class OfferController {

	@Autowired
	private OfferService offerService;

	@GetMapping()
	public ResponseEntity<GenericResponse<List<OfferDTO>>> listOffers(@RequestParam int limit, @RequestParam int offset) {

		Response<List<OfferDTO>> response = new Response<>();
		response.setResult(offerService.getAll(true));
		GenericResponse<List<OfferDTO>> reponse = new GenericResponse<List<OfferDTO>>(response,
				"Offers retrived successfully", true);
		return ResponseEntity.status(HttpStatus.OK).body(reponse);

	}
}
