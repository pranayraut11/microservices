package com.ecors.core.ui.response;

import java.util.Optional;

public class Response<T> {

	private Optional<T> result;

	public Optional<T> getResult() {
		return result;
	}

	public void setResult(Optional<T> result) {
		this.result = result;
	}

}
