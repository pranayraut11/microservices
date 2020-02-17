package com.ecors.product.controller;

import java.util.Collection;
import java.util.List;

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
import com.ecors.product.DTO.OfferDTO;
import com.ecors.product.DTO.SubCategoryDTO;
import com.ecors.product.service.OfferService;

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

	@GetMapping("{id}/subCategories")
	public ResponseEntity<GenericResponse<List<SubCategoryDTO>>> listSubCategoriesByOffer(@PathVariable int id,
			@RequestParam int limit, @RequestParam int offset) {
		Response<List<SubCategoryDTO>> response = new Response<>();
		response.setResult(offerService.getAllSubCateogryByOffer(id, false));
		GenericResponse<List<SubCategoryDTO>> reponse = new GenericResponse<List<SubCategoryDTO>>(response,
				"Data retrived successfully", true);
		return ResponseEntity.status(HttpStatus.OK).body(reponse);

	}
}
