package com.todo.list.controller.builder.page;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.todo.list.controller.dto.BackGroundDTO;
import com.todo.list.controller.dto.user.UserDTO;

public class PageImageBuilder implements PageBuilder<PageImageBuilder, BackGroundDTO> {

	private List<BackGroundDTO> dtos;

	private int number;

	private int totalPages;

	private long totalElement;

	private long size;

	private int numberOfElements;

	private Pageable pageable;

	@Override
	public BackGroundDTO builder() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <K> PageImageBuilder setLists(K lists) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageImageBuilder setNumber(int number) {
		// TODO Auto-generated method stub
		this.number = number;
		return this;
	}

	@Override
	public PageImageBuilder setTotalPages(int totalPages) {
		// TODO Auto-generated method stub
		this.totalPages = totalPages;
		return this;
	}

	@Override
	public PageImageBuilder setTotalElements(long totalElement) {
		// TODO Auto-generated method stub
		this.totalElement = totalElement;
		return this;
	}

	@Override
	public PageImageBuilder setSize(long size) {
		// TODO Auto-generated method stub
		this.size = size;
		return this;
	}

	@Override
	public PageImageBuilder setNumberOfElements(int numberOfElements) {
		// TODO Auto-generated method stub
		this.numberOfElements = numberOfElements;
		return this;
	}

	@Override
	public PageImageBuilder setPageable(Pageable pageable) {
		// TODO Auto-generated method stub
		this.pageable = pageable;
		return this;
	}

}
