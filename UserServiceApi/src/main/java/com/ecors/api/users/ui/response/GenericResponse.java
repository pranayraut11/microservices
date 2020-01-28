package com.ecors.api.users.ui.response;

public class GenericResponse<T> {

	private Response<T> data;
	private String message;
	private boolean success;
	private String errorMessage;

	public GenericResponse(Response<T> data, String message, boolean success) {
		this.data = data;
		this.message = message;
		this.success = success;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public Response<T> getData() {
		return data;
	}

	public void setData(Response<T> data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

}