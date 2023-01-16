package com.todo.list.controller;

import org.springframework.http.HttpStatus;

public class ResponseMessageEntity<T> {

	T returnValue;

	ResponseStatus responseStatus;

	public ResponseMessageEntity() {
		// TODO Auto-generated constructor stub
	}

	public ResponseMessageEntity(T returnValue, ResponseStatus responseStatus) {
		super();
		this.returnValue = returnValue;
		this.responseStatus = responseStatus;
	}

	public T getReturnValue() {
		return returnValue;
	}

	public void setReturnValue(T returnValue) {
		this.returnValue = returnValue;
	}

	public ResponseStatus getResponseStatus() {
		return responseStatus;
	}

	public void setResponseStatus(ResponseStatus responseStatus) {
		this.responseStatus = responseStatus;
	}

}
