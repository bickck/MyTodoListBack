package com.todo.list.controller.builder.page;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Pageable;

import com.todo.list.controller.builder.QuoteBuilder;
import com.todo.list.controller.dto.PageQuote;
import com.todo.list.controller.dto.QuoteDTO;
import com.todo.list.entity.UserQuoteEntity;

public class PageQuoteBuilder implements PageBuilder<PageQuoteBuilder, PageQuote> {

	private List<QuoteDTO> dtos;
	private int number;
	private int totalPages;
	private long totalElement;
	private long size;
	private Pageable pageable;
	private int numberOfElements;

	@Override
	public PageQuote builder() {
		// TODO Auto-generated method stub
		return new PageQuote(dtos, number, totalPages, totalElement, size, numberOfElements, pageable);
	}

	@Override
	public <K> PageQuoteBuilder setLists(K lists) {
		// TODO Auto-generated method stub
		List<UserQuoteEntity> entity = (List<UserQuoteEntity>) lists;
		List<QuoteDTO> dtos = new ArrayList<QuoteDTO>();

		for (UserQuoteEntity entities : entity) {
			QuoteBuilder builder = new QuoteBuilder();
			builder.setId(entities.getId());
			builder.setQuote(entities.getQuote());
			builder.setAuthor(entities.getAuthor());

			dtos.add(builder.builder());
		}
		this.dtos = dtos;

		return this;
	}

	@Override
	public PageQuoteBuilder setNumber(int number) {
		// TODO Auto-generated method stub
		this.number = number;
		return this;
	}

	@Override
	public PageQuoteBuilder setTotalPages(int totalPages) {
		// TODO Auto-generated method stub
		this.totalPages = totalPages;
		return this;
	}

	@Override
	public PageQuoteBuilder setTotalElements(long totalElement) {
		// TODO Auto-generated method stub
		this.totalElement = totalElement;
		return this;
	}

	@Override
	public PageQuoteBuilder setSize(long size) {
		// TODO Auto-generated method stub
		this.size = size;
		return this;
	}

	@Override
	public PageQuoteBuilder setNumberOfElements(int numberOfElements) {
		// TODO Auto-generated method stub
		this.numberOfElements = numberOfElements;
		return this;
	}

	@Override
	public PageQuoteBuilder setPageable(Pageable pageable) {
		// TODO Auto-generated method stub
		this.pageable = pageable;
		return this;
	}

}
