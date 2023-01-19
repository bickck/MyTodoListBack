package com.todo.list.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.todo.list.controller.ResponseStatus;
import com.todo.list.controller.response.message.ResponseErrorMessageEntity;
import com.todo.list.service.user.TodoService;
import com.todo.list.service.user.UserService;

@RestControllerAdvice(basePackageClasses = { TodoService.class, UserService.class })
public class ServiceExceptionHandler {

	@ExceptionHandler(value = NullPointerException.class)
	public ResponseErrorMessageEntity<?> userServiceNullPointerException(Exception exception) {

		

		return new ResponseErrorMessageEntity<String>(exception.getMessage(), HttpStatus.OK);
	}
}
