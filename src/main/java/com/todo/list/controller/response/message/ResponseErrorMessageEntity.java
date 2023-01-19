package com.todo.list.controller.response.message;

import org.springframework.http.HttpStatus;

import com.todo.list.controller.response.ResponseCode;

public class ResponseErrorMessageEntity<T> extends ResponseCode {

	T errorMessage;

	String responseStatus;

	public ResponseErrorMessageEntity() {
		// TODO Auto-generated constructor stub
	}

	public ResponseErrorMessageEntity(T errorMessage, HttpStatus httpStatus) {
		super();
		this.errorMessage = errorMessage;
		this.responseStatus = "";
		super.setCode(httpStatus);
	}

	public ResponseErrorMessageEntity(T errorMessage, String responseStatus, HttpStatus httpStatus) {
		super();
		this.errorMessage = errorMessage;
		this.responseStatus = responseStatus;
		super.setCode(httpStatus);
	}

	public T getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(T errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getResponseStatus() {
		return responseStatus;
	}

	public void setResponseStatus(String responseStatus) {
		this.responseStatus = responseStatus;
	}

}
