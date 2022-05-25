package com.todo.list.exception;

import java.io.IOException;

import javax.security.sasl.AuthenticationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.ResponseErrorHandler;

import com.todo.list.controller.AuthController;

@RestControllerAdvice(basePackageClasses = { AuthController.class })
public class AuthtExcetionHandler {

	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<String> unExsistUser(Exception exception) {
		exception.printStackTrace();
		return new ResponseEntity<String>("not user fail", HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(AuthenticationException.class)
	public ResponseEntity<String> notAcceptAuthentication(Exception exception) {
		exception.printStackTrace();
		return new ResponseEntity<String>("auth Fail", HttpStatus.BAD_REQUEST);

	}
}
