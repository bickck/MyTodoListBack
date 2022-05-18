package com.todo.list.controller.builder.page;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.data.domain.Pageable;

import com.todo.list.controller.dto.PageUserDTO;
import com.todo.list.controller.dto.UserDTO;
import com.todo.list.entity.UserEntity;

public class PageUserBuilder implements PageBuilder<PageUserBuilder, PageUser> {

	private List<UserDTO> dtos;

	private int number;

	private int totalPages;

	private long totalElement;

	private long size;

	private int numberOfElements;

	private Pageable pageable;

	@Override
	public PageUser builder() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <K> PageUserBuilder setLists(K lists) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageUserBuilder setNumber(int number) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageUserBuilder setTotalPages(int totalPages) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageUserBuilder setTotalElements(long totalElement) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageUserBuilder setSize(long size) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageUserBuilder setNumberOfElements(int numberOfElements) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageUserBuilder setPageable(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
