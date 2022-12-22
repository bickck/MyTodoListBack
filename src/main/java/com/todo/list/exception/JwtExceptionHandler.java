package com.todo.list.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.todo.list.configs.token.AuthenticationJwtProvider;

import io.jsonwebtoken.ExpiredJwtException;

@RestControllerAdvice(basePackageClasses = { AuthenticationJwtProvider.class })
public class JwtExceptionHandler {

//	@ExceptionHandler(ExpiredJwtException.class)
//	public ResponseEntity<String> expiredJwtExceptionHandler(Exception exception) {
//		
//		
//		
//		return new ResponseEntity<String>("", HttpStatus.FORBIDDEN);
//	}

}
