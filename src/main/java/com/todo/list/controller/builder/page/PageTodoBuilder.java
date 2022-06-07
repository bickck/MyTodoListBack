package com.todo.list.controller.builder.page;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Pageable;

import com.todo.list.controller.builder.TodoBuilder;
import com.todo.list.controller.dto.page.PageTodoDTO;
import com.todo.list.controller.dto.service.TodoDTO;
import com.todo.list.entity.TodoEntity;

public class PageTodoBuilder implements PageBuilder<PageTodoBuilder, PageTodoDTO> {

	private List<TodoDTO> dtos;
	private int number;
	private int totalPages;
	private long totalElement;
	private long size;
	private Pageable pageable;
	private int numberOfElements;

	@Override
	public PageTodoDTO builder() {
		// TODO Auto-generated method stub
		return new PageTodoDTO(dtos, number, totalPages, totalElement, size, numberOfElements);
	}

	@Override
	public <K> PageTodoBuilder setLists(K lists) {
		// TODO Auto-generated method stub
		List<TodoEntity> entity = (List<TodoEntity>) lists;
		List<TodoDTO> dtos = new ArrayList<TodoDTO>();

		for (TodoEntity entities : entity) {
			TodoBuilder builder = new TodoBuilder();
			builder.setId(entities.getId());
			builder.setUsername(entities.getUser().getUsername());
			builder.setTitle(entities.getTitle());
			builder.setContent(entities.getContent());
			builder.setDate(entities.getCreateDate());

			dtos.add(builder.builder());
		}
		this.dtos = dtos;
		return this;
	}

	@Override
	public PageTodoBuilder setNumber(int number) {
		// TODO Auto-generated method stub
		this.number = number;
		return this;
	}

	@Override
	public PageTodoBuilder setTotalPages(int totalPages) {
		// TODO Auto-generated method stub
		this.totalPages = totalPages;
		return this;
	}

	@Override
	public PageTodoBuilder setTotalElements(long totalElement) {
		// TODO Auto-generated method stub
		this.totalElement = totalElement;
		return this;
	}

	@Override
	public PageTodoBuilder setSize(long size) {
		// TODO Auto-generated method stub
		this.size = size;
		return this;
	}

	@Override
	public PageTodoBuilder setNumberOfElements(int numberOfElements) {
		// TODO Auto-generated method stub
		this.numberOfElements = numberOfElements;
		return this;
	}

	@Override
	public PageTodoBuilder setPageable(Pageable pageable) {
		// TODO Auto-generated method stub
		this.pageable = pageable;
		return this;
	}

}
