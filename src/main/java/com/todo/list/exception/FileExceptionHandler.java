package com.todo.list.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class FileExceptionHandler {

	@ExceptionHandler
	public String fileSaveExceptionHandler() {
		return "";
	}
}
