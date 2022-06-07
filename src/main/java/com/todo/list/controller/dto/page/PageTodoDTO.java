package com.todo.list.controller.dto.page;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.todo.list.controller.dto.service.TodoDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


public class PageTodoDTO {
	private List<TodoDTO> todoDTOs;

	private int number;

	private int totalPages;

	private long totalElement;

	private long size;

	private int numberOfElements;

	private Pageable pageable;

	public PageTodoDTO() {
		// TODO Auto-generated constructor stub
	}
	@JsonIgnoreProperties(ignoreUnknown = true)
	public PageTodoDTO(List<TodoDTO> todoDTOs, int number, int totalPages, long totalElement, long size,
			int numberOfElements) {
		super();
		this.todoDTOs = todoDTOs;
		this.number = number;
		this.totalPages = totalPages;
		this.totalElement = totalElement;
		this.size = size;
		this.numberOfElements = numberOfElements;
	}
	@JsonIgnoreProperties(ignoreUnknown = true)
	public PageTodoDTO(List<TodoDTO> todoDTOs, int number, int totalPages, long totalElement, long size,
			int numberOfElements, Pageable pageable) {
		super();
		this.todoDTOs = todoDTOs;
		this.number = number;
		this.totalPages = totalPages;
		this.totalElement = totalElement;
		this.size = size;
		this.numberOfElements = numberOfElements;
		//this.pageable = pageable;
	}

	public List<TodoDTO> getTodoDTOs() {
		return todoDTOs;
	}

	public void setTodoDTOs(List<TodoDTO> todoDTOs) {
		this.todoDTOs = todoDTOs;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public long getTotalElement() {
		return totalElement;
	}

	public void setTotalElement(long totalElement) {
		this.totalElement = totalElement;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public int getNumberOfElements() {
		return numberOfElements;
	}

	public void setNumberOfElements(int numberOfElements) {
		this.numberOfElements = numberOfElements;
	}

//	public Pageable getPageable() {
//		return pageable;
//	}
//
//	public void setPageable(Pageable pageable) {
//		this.pageable = pageable;
//	}
}
