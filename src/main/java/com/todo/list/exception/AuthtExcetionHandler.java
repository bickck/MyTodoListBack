package com.todo.list.exception;

import javax.security.sasl.AuthenticationException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.todo.list.controller.main.AuthController;
import com.todo.list.controller.response.message.ResponseErrorMessageEntity;
import com.todo.list.exception.custom.ArgumentValidException;
import com.todo.list.service.main.UserAuthService;

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
