package com.todo.list.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.todo.list.controller.response.message.ResponseErrorMessageEntity;
import com.todo.list.service.main.TodoService;
import com.todo.list.service.main.UserService;

@RestControllerAdvice(basePackageClasses = { TodoService.class, UserService.class })
public class ServiceExceptionHandler {

	@ExceptionHandler(value = NullPointerException.class)
	public ResponseErrorMessageEntity<?> userServiceNullPointerException(Exception exception) {

		

		return new ResponseErrorMessageEntity<String>(exception.getMessage(), HttpStatus.OK);
	}
}
