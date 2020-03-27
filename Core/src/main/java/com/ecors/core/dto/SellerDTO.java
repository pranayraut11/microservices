package com.ecors.core.dto;

public class SellerDTO extends BaseUserDTO {

	private String orgnizantionName;
	float rating;

	public String getOrgnizantionName() {
		return orgnizantionName;
	}

	public void setOrgnizantionName(String orgnizantionName) {
		this.orgnizantionName = orgnizantionName;
	}

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}

}
