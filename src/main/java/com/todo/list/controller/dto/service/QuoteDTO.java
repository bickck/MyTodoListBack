package com.todo.list.controller.dto.service;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.todo.list.entity.base.AdminQuoteEntity;

import lombok.Data;
import lombok.Getter;

public class QuoteDTO implements Serializable{

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

	public QuoteDTO(Long id, String quote, String author) {
		this.id = id;
		this.quote = quote;
		this.author = author;
	}

	public QuoteDTO(@NotNull Long id, @NotNull AdminQuoteEntity quote) {
		this.id = id;
		this.quote = quote.getQuote();
		this.author = quote.getAuthor();
	}

	public QuoteDTO(@NotNull AdminQuoteEntity queto) {
		this.id = queto.getId();
		this.quote = queto.getQuote();
		this.author = queto.getAuthor();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getQuote() {
		return quote;
	}

	public void setQuote(String quote) {
		this.quote = quote;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

}
