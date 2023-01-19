package com.todo.list.exception;

import java.io.IOException;

import javax.security.sasl.AuthenticationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.ResponseErrorHandler;

import com.todo.list.controller.ResponseStatus;
import com.todo.list.controller.main.AuthController;
import com.todo.list.controller.response.message.ResponseErrorMessageEntity;
import com.todo.list.exception.custom.ArgumentValidException;
import com.todo.list.service.user.UserAuthService;

@RestControllerAdvice(basePackageClasses = { AuthController.class, UserAuthService.class })
public class AuthtExcetionHandler {

	@ExceptionHandler(NullPointerException.class)
	public ResponseErrorMessageEntity<?> isNotExsistUserAccount(Exception exception) {

		return new ResponseErrorMessageEntity<String>(exception.getMessage(), HttpStatus.FORBIDDEN);
	}

	@ExceptionHandler(ArgumentValidException.class)
	public ResponseErrorMessageEntity<?> argumentValidException(Exception exception) {

		return new ResponseErrorMessageEntity<String>(exception.getMessage(), HttpStatus.FORBIDDEN);
	}

	@ExceptionHandler(AuthenticationException.class)
	public ResponseErrorMessageEntity<?> doesNotSameUserEmailException(Exception exception) {

		return new ResponseErrorMessageEntity<String>(exception.getMessage(), HttpStatus.UNAUTHORIZED);
	}
}
