package com.todo.list.controller.response;

import org.springframework.http.HttpStatus;

public class ResponseCode {

	protected HttpStatus code;

	public ResponseCode() {
		// TODO Auto-generated constructor stub
	}

	public HttpStatus getCode() {
		return code;
	}

	public void setCode(HttpStatus code) {
		this.code = code;
	}

}
