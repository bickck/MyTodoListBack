package com.todo.list.test;

import java.util.List;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.AbstractPageRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.data.web.config.PageableHandlerMethodArgumentResolverCustomizer;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PageableTest<T> extends PageImpl<T> {

	public PageableTest(List<T> content) {
		super(content);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<T> getContent() {
		// TODO Auto-generated method stub
		return super.getContent();
	}

	@Override
	public int getNumber() {
		// TODO Auto-generated method stub
		return super.getNumber();
	}

	@Override
	public int getNumberOfElements() {
		// TODO Auto-generated method stub
		return super.getNumberOfElements();
	}

	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return super.getSize();
	}

	@Override
	public Sort getSort() {
		// TODO Auto-generated method stub
		return super.getSort();
	}

	@Override
	public boolean hasContent() {
		// TODO Auto-generated method stub
		return super.hasContent();
	}

	@Override
	public boolean hasPrevious() {
		// TODO Auto-generated method stub
		return super.hasPrevious();
	}

	@Override
	public boolean isFirst() {
		// TODO Auto-generated method stub
		return super.isFirst();
	}
}
