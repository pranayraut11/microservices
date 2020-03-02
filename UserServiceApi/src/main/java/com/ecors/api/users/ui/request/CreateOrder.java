package com.ecors.api.users.ui.request;

import java.util.List;

public class CreateOrder {

	private List<Integer> productIds;

	public List<Integer> getProductIds() {
		return productIds;
	}

	public void setProductIds(List<Integer> productIds) {
		this.productIds = productIds;
	}

}
