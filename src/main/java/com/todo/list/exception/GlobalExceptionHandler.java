package com.todo.list.exception;

import java.sql.SQLIntegrityConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	

	@ExceptionHandler(SQLIntegrityConstraintViolationException.class)
	public ResponseEntity<String> SQLIntegrityConstraintViolationExceptionHandler(Exception exception) {
		return new ResponseEntity<String>("", HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(IllegalAccessError.class)
	public ResponseEntity<String> IllegalAccessErrorHandler(Exception exception) {
		return new ResponseEntity<String>("", HttpStatus.NOT_FOUND);
	}
	
	/**
	 * 모든 에러를 클라이언트에 보내지 않음
	 * 
	 * @param exception
	 * @return
	 */

	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> globalExceptionErrorHandler(Exception exception){
		exception.printStackTrace();
		return new ResponseEntity<String>("server error", HttpStatus.NOT_FOUND);
	}
}
