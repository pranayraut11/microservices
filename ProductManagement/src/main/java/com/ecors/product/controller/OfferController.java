package com.ecors.product.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecors.core.ui.response.GenericResponse;
import com.ecors.product.DTO.OfferDTO;

@RestController
@RequestMapping("offers")
public class OfferController {

//	@GetMapping
//	public ResponseEntity<GenericResponse<List<OfferDTO>>> listOffer(@RequestParam String offerName,
//			@RequestParam int limit, @RequestParam int offset) {
//
//		
//		
//	}
}
