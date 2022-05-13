package com.todo.list.controller.builder;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.todo.list.controller.dto.UserDTO;

public class PageUserBuilder {
	private List<UserDTO> userDTO;

	private int number;

	private int totalPages;

	private long totalElement;

	private long size;

	private int numberOfElements;

	private Pageable pageable;

	public PageUserBuilder setUserDTO(List<UserDTO> userDTO) {
		this.userDTO = userDTO;
		return this;
	}

	public PageUserBuilder setNumber(int number) {
		this.number = number;
		return this;
	}

	public PageUserBuilder setTotalPages(int totalPages) {
		this.totalPages = totalPages;
		return this;
	}

	public PageUserBuilder setTotalElement(long totalElement) {
		this.totalElement = totalElement;
		return this;
	}

	public PageUserBuilder setSize(long size) {
		this.size = size;
		return this;
	}

	public PageUserBuilder setNumberOfElements(int numberOfElements) {
		this.numberOfElements = numberOfElements;
		return this;
	}

	public PageUserBuilder setPageable(Pageable pageable) {
		this.pageable = pageable;
		return this;
	}
}
