package com.ecors.category.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecors.category.service.SubCategoryService;
import com.ecors.core.dto.SubCategoryDTO;
import com.ecors.core.ui.response.GenericResponse;
import com.ecors.core.ui.response.Response;

@RestController
@RequestMapping("subcategories")
public class SubCategoryController {

	@Autowired
	private SubCategoryService subCategoryService;

	@GetMapping
	public ResponseEntity<GenericResponse<List<SubCategoryDTO>>> listSubCategories(@RequestParam int limit,
			@RequestParam int offset) {

		Response<List<SubCategoryDTO>> response = new Response<>();
		response.setResult(subCategoryService.getAllSubCateogry(true));
		GenericResponse<List<SubCategoryDTO>> reponse = new GenericResponse<List<SubCategoryDTO>>(response,
				"Data retrived successfully", true);
		return ResponseEntity.status(HttpStatus.OK).body(reponse);

	}

}
