package com.todo.list.controller.builder;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.data.domain.Pageable;

import com.todo.list.controller.dto.PageUserDTO;
import com.todo.list.controller.dto.UserDTO;
import com.todo.list.entity.UserEntity;

public class PageUserBuilder {

	private List<UserDTO> dtos;

	private int number;

	private int totalPages;

	private long totalElement;

	private long size;

	private int numberOfElements;

	private Pageable pageable;

	public PageUserBuilder setUserDTO(List<UserEntity> userEntities) {
		Iterator<UserEntity> itr = userEntities.listIterator();
		List<UserDTO> lists = new ArrayList<UserDTO>();
		while (itr.hasNext()) {
			UserBuilder builder = new UserBuilder();
			builder.setUserName(itr.next().getUsername());
			builder.setPassword(itr.next().getPassword());
			lists.add(builder.dtoBuilder());
		}
		this.dtos = lists;
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

	public PageUserBuilder setTotalElements(long totalElement) {
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

	public PageUserDTO builder() {
		return new PageUserDTO(dtos, number, totalPages, totalElement, size, numberOfElements, pageable);
	}
}
