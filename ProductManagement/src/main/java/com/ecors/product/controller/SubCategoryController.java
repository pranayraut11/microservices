package com.ecors.product.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecors.core.ui.response.GenericResponse;
import com.ecors.product.DTO.SubCategoryDTO;

@RestController
@RequestMapping("subcategories")
public class SubCategoryController {

	@GetMapping
	public ResponseEntity<GenericResponse<List<SubCategoryDTO>>> listSubCategoriesByOffername(
			@RequestParam String offerName, @RequestParam int limit, @RequestParam int offset) {

		GenericResponse<List<SubCategoryDTO>> reponse = new GenericResponse<List<SubCategoryDTO>>(null, offerName, false);
		return ResponseEntity.status(HttpStatus.OK).body(reponse);

	}

}
