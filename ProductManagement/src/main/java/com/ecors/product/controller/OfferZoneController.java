package com.ecors.product.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecors.core.ui.response.GenericResponse;
import com.ecors.core.ui.response.OfferZoneProduct;

@RestController
@RequestMapping("offers")
public class OfferZoneController {

	@GetMapping("dealoftheday")
	public ResponseEntity<GenericResponse<OfferZoneProduct>> dealOfTheDay(@RequestParam int limit, @RequestParam int offset) {

		return null;
	}

}
