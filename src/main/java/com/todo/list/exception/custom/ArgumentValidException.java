package com.todo.list.exception.custom;

import org.springframework.validation.FieldError;

public class ArgumentValidException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String e;

	private FieldError fieldError;

	public ArgumentValidException(String e) {
		this.e = e;
	}

	public ArgumentValidException(FieldError fieldError) {
		this.fieldError = fieldError;
	}

	public String getE() {
		return e;
	}

	public void setE(String e) {
		this.e = e;
	}

	public FieldError getFieldError() {
		return fieldError;
	}

	public void setFieldError(FieldError fieldError) {
		this.fieldError = fieldError;
	}

}
