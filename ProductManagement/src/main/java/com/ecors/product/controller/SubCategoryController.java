package com.ecors.product.controller;

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
import com.ecors.product.DTO.OfferZoneDTO;
import com.ecors.product.DTO.SubCategoryDTO;
import com.ecors.product.service.OfferService;
import com.ecors.product.service.SubCategoryService;

@RestController
@RequestMapping("subcategories")
public class SubCategoryController {

	@Autowired
	private SubCategoryService subCategoryService;
	
	@Autowired
	private OfferService offerService;

	@GetMapping
	public ResponseEntity<GenericResponse<List<SubCategoryDTO>>> listSubCategories(@RequestParam int limit,
			@RequestParam int offset) {

		Response<List<SubCategoryDTO>> response = new Response<>();
		response.setResult(subCategoryService.getAllSubCateogry(true));
		GenericResponse<List<SubCategoryDTO>> reponse = new GenericResponse<List<SubCategoryDTO>>(response,
				"Data retrived successfully", true);
		return ResponseEntity.status(HttpStatus.OK).body(reponse);

	}
	
	

	@GetMapping("/{offername}")
	public ResponseEntity<GenericResponse<List<SubCategoryDTO>>> listSubCategoriesByOffername(@PathVariable String offername,
			@RequestParam int limit, @RequestParam int offset) {

		Response<OfferZoneDTO> response = new Response<>();
		response.setResult(subCategoryService.getAllSubCateogry(offername, true));
		GenericResponse<OfferZoneDTO> reponse = new GenericResponse<OfferZoneDTO>(response,
				"Data retrived successfully", true);
		return ResponseEntity.status(HttpStatus.OK).body(reponse);

	}

}