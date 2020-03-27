package com.ecors.category.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class SubCategorySpecification {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "subCategoryId")
	private SubCategory subCategory;

	private String specificationPayload;

	private String highLightsPayload;

	private String type;

	public String getSpecificationPayload() {
		return specificationPayload;
	}

	public void setSpecificationPayload(String specificationPayload) {
		this.specificationPayload = specificationPayload;
	}

	public String getHighLightsPayload() {
		return highLightsPayload;
	}

	public void setHighLightsPayload(String highLightsPayload) {
		this.highLightsPayload = highLightsPayload;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public SubCategory getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(SubCategory subCategory) {
		this.subCategory = subCategory;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
