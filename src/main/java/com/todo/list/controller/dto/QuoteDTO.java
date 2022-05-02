package com.todo.list.controller.dto;

import javax.validation.constraints.NotNull;

import com.todo.list.domain.base.DefaultQuoteEntity;

import lombok.Data;
import lombok.Getter;

public class QuoteDTO {

	private Long id;
	private String quote;
	private String author;

	public QuoteDTO() {
		// TODO Auto-generated constructor stub
	}

	public QuoteDTO(@NotNull String quote, @NotNull String author) {
		super();
		this.quote = quote;
		this.author = author;
	}

	public QuoteDTO(@NotNull Long id, @NotNull DefaultQuoteEntity quote) {
		this.id = id;
		this.quote = quote.getQuote();
		this.author = quote.getAuthor();
	}

	public QuoteDTO(@NotNull DefaultQuoteEntity queto) {
		this.id = queto.getId();
		this.quote = queto.getQuote();
		this.author = queto.getAuthor();
	}

	@Override
	public String toString() {
		return "Queto [queto=" + quote + ", author=" + author + "]";
	}

	public String getQueto() {
		return quote;
	}

	public void setQueto(String queto) {
		this.quote = queto;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

}
