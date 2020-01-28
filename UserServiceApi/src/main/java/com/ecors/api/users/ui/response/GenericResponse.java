package com.ecors.api.users.ui.response;

public class GenericResponse<T> {

	private Response<T> response;

	public Response<T> getResponse() {
		return response;
	}

	public void setResponse(Response<T> response) {
		this.response = response;
	}

}
