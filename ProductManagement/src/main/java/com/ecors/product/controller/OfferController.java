package com.ecors.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.ecors.core.ui.response.GenericResponse;
import com.ecors.core.ui.response.Response;
import com.ecors.product.DTO.OfferZoneDTO;
import com.ecors.product.service.OfferService;

@RestController
@RequestMapping("offers")
public class OfferController {

	@Autowired
	private OfferService offerService;

	@GetMapping()
	public ResponseEntity<GenericResponse<List<OfferZoneDTO>>> listOffers(@RequestParam int limit, @RequestParam int offset) {

		Response<List<OfferZoneDTO>> response = new Response<>();
		response.setResult(offerService.getAll(true));
		GenericResponse<List<OfferZoneDTO>> reponse = new GenericResponse<List<OfferZoneDTO>>(response,
				"Offers retrived successfully", true);
		return ResponseEntity.status(HttpStatus.OK).body(reponse);

	}
}
