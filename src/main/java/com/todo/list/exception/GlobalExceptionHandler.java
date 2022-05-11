package com.todo.list.exception;

import java.sql.SQLIntegrityConstraintViolationException;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	
	@ExceptionHandler(SQLIntegrityConstraintViolationException.class)
	public String SQLIntegrityConstraintViolationExceptionHandler() {
		return "fail";
	}
	
}
