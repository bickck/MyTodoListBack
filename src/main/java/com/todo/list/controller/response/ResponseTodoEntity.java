package com.todo.list.controller.response;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.todo.list.controller.dto.page.PageTodoDTO;


public class ResponseTodoEntity {

	private PageTodoDTO pageTodoDTO;

	private HttpHeaders httpHeaders;

	private HttpStatus httpStatus;

	public ResponseTodoEntity() {
		// TODO Auto-generated constructor stub
	}
	public ResponseTodoEntity(PageTodoDTO pageTodoDTO, HttpStatus httpStatus) {
		this.pageTodoDTO = pageTodoDTO;
		this.httpStatus = httpStatus;
	}

	public ResponseTodoEntity(PageTodoDTO pageTodoDTO, HttpHeaders headers, HttpStatus httpStatus) {
		this.pageTodoDTO = pageTodoDTO;
		this.httpHeaders = headers;
		this.httpStatus = httpStatus;
	}

	public PageTodoDTO getPageTodoDTO() {
		return pageTodoDTO;
	}

	public void setPageTodoDTO(PageTodoDTO pageTodoDTO) {
		this.pageTodoDTO = pageTodoDTO;
	}

	public HttpHeaders getHttpHeaders() {
		return httpHeaders;
	}

	public void setHttpHeaders(HttpHeaders httpHeaders) {
		this.httpHeaders = httpHeaders;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

}
