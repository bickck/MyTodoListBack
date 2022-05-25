package com.todo.list.exception;

import java.sql.SQLIntegrityConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	

	@ExceptionHandler(SQLIntegrityConstraintViolationException.class)
	public String SQLIntegrityConstraintViolationExceptionHandler(Exception exception) {
		return "fail";
	}

	@ExceptionHandler(IllegalAccessError.class)
	public ResponseEntity<String> IllegalAccessErrorHandler(Exception exception) {
		return new ResponseEntity<String>(" ", HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> globalExceptionErrorHandler(Exception exception){
		exception.printStackTrace();
		return new ResponseEntity<String>("server error", HttpStatus.NOT_FOUND);
	}
}
