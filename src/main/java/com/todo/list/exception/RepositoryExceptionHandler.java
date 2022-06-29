package com.todo.list.exception;

import java.util.NoSuchElementException;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(basePackages = "com.todo.list.repository.*")
public class RepositoryExceptionHandler {

	@ExceptionHandler(value = NoSuchElementException.class)
	public String noSuchElementException(Exception exception) {
		exception.printStackTrace();
		return "데이터가 없어요";
	}
}
