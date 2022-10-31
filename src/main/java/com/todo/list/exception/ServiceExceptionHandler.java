package com.todo.list.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.todo.list.service.user.TodoService;
import com.todo.list.service.user.UserService;

@RestControllerAdvice(basePackageClasses = {
		TodoService.class,
		UserService.class
})
public class ServiceExceptionHandler {

//	@ExceptionHandler()
//	public ResponseEntity<?> userServiceExceptionHandler(){
//		
//		return new ResponseEntity<>(HttpStatus.OK);
//	}
}
