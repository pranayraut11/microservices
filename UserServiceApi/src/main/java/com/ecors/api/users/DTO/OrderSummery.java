package com.ecors.api.users.DTO;

import java.util.List;

import com.ecors.core.dto.ProductDTO;

public class OrderSummery {

	private AddressDTO address;
	private List<ProductDTO> products;
	private Integer totalAmount;
	private Integer deliveryFee;
	private Integer savedAmmount;
	
}
