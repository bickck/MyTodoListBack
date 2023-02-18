package com.todo.list.exception;

import org.springframework.http.HttpStatus;
import org.springframework.messaging.handler.annotation.support.MethodArgumentNotValidException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.todo.list.controller.main.AuthController;
import com.todo.list.controller.main.QuoteController;
import com.todo.list.controller.main.TodoController;
import com.todo.list.controller.main.UserController;
import com.todo.list.controller.response.message.ResponseErrorMessageEntity;
import com.todo.list.exception.custom.ArgumentValidException;
import com.todo.list.test.controller.ValidTestController;

@RestControllerAdvice(basePackageClasses = { UserController.class, TodoController.class, QuoteController.class,
		ValidTestController.class, AuthController.class })
public class ArgumentValidExceptionHandler {

	@ExceptionHandler(ArgumentValidException.class)
	public ResponseErrorMessageEntity<?> unExsistUser(ArgumentValidException exception) {

		return new ResponseErrorMessageEntity<FieldError>(exception.getFieldError(), HttpStatus.FORBIDDEN);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseErrorMessageEntity<?> requestArgumentNotMatched(MethodArgumentNotValidException exception) {

		FieldError errors = exception.getBindingResult().getFieldError();

		return new ResponseErrorMessageEntity<FieldError>(errors, HttpStatus.BAD_REQUEST);
	}
}
