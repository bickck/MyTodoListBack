package com.todo.list.controller.response.message;

import org.springframework.http.HttpStatus;

import com.todo.list.controller.ResponseStatus;
import com.todo.list.controller.response.ResponseCode;

public class ResponseMessageEntity<T> extends ResponseCode {

	T message;

	public ResponseMessageEntity() {
		// TODO Auto-generated constructor stub
	}

	public ResponseMessageEntity(T message, HttpStatus httpStatus) {
		super();
		this.message = message;
		super.setCode(httpStatus);
	}

	public T getReturnValue() {
		return message;
	}

	public void setReturnValue(T returnValue) {
		this.message = returnValue;
	}

}
