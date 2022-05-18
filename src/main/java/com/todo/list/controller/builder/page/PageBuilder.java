package com.todo.list.controller.builder.page;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.todo.list.controller.dto.PageUserDTO;

public interface PageBuilder<T, O> {

	public O builder();

	public <K> T setLists(K lists);

	public T setNumber(int number);

	public T setTotalPages(int totalPages);

	public T setTotalElements(long totalElement);

	public T setSize(long size);

	public T setNumberOfElements(int numberOfElements);

	public T setPageable(Pageable pageable);
}
