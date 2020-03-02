package com.ecors.api.users.ui.request;

import com.ecors.api.users.enums.AddressType;

public class UpdateDeliveryAddress {
	private AddressType type;

	public AddressType getType() {
		return type;
	}

	public void setType(AddressType type) {
		this.type = type;
	}

}
