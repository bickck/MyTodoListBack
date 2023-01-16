package com.todo.list.controller;

import org.springframework.http.HttpStatus;

public class ResponseErrorMessageEntity<T> {

	T errorMessage;

	HttpStatus httpStatus;

	ResponseStatus responseStatus;

	public ResponseErrorMessageEntity() {
		// TODO Auto-generated constructor stub
	}

	public ResponseErrorMessageEntity(T errorMessage, HttpStatus httpStatus, ResponseStatus responseStatus) {
		super();
		this.errorMessage = errorMessage;
		this.httpStatus = httpStatus;
		this.responseStatus = responseStatus;
	}

	public T getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(T errorMessage) {
		this.errorMessage = errorMessage;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

	public ResponseStatus getResponseStatus() {
		return responseStatus;
	}

	public void setResponseStatus(ResponseStatus responseStatus) {
		this.responseStatus = responseStatus;
	}

}
