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

//@JsonIgnoreProperties(ignoreUnknown = true, value = { "pageable" })
public class PageableTest<T>  {

//	@JsonCreator(mode = Mode.PROPERTIES)
//	public PageableTest(
//			@JsonProperty("content") List<T> content, 
//			@JsonProperty("number") int page,
//			@JsonProperty("size") int size, 
//			@JsonProperty("totalElements") long total) {
//		super(content, PageRequest.of(page, size), total);
//		// TODO Auto-generated constructor stub
//	}
//
//	public PageableTest(Page<T> pages) {
//		super(pages.getContent(),pages.getPageable(),pages.getTotalElements());
//	}
}
