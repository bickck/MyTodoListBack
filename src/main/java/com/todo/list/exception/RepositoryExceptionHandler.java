package com.todo.list.exception;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackages = "com.todo.list.repository.*")
public class RepositoryExceptionHandler {

	@ExceptionHandler(value = NoSuchElementException.class)
	public ResponseEntity<?> noSuchElementException(Exception exception) {
		exception.printStackTrace();
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
