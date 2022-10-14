package com.todo.list.controller.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.todo.list.entity.QuoteEntity;
import com.todo.list.entity.base.AdminQuoteEntity;
import com.todo.list.entity.base.Publish;

import lombok.Data;
import lombok.Getter;

public class QuoteDTO implements Serializable {

	private Long id;
	private String quote;
	private String author;
	private String isPublish;
	private String username;
	private Long heart;

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

	public QuoteDTO(String quote, String author, String isPublish, Long heart) {
		super();
		this.quote = quote;
		this.author = author;
		this.isPublish = isPublish;
		this.heart = heart;
	}

	public QuoteDTO(Long id, String quote, String author, String isPublish, String username, Long heart) {
		super();
		this.id = id;
		this.quote = quote;
		this.author = author;
		this.isPublish = isPublish;
		this.username = username;
		this.heart = heart;
	}
	
	public QuoteDTO(QuoteEntity quoteEntity) {
		super();
		this.id = quoteEntity.getId();
		this.quote = quoteEntity.getQuote();
		this.author = quoteEntity.getAuthor();
		this.isPublish = quoteEntity.getIsPublish().toString();
		this.username = quoteEntity.getUser().getUsername();
		this.heart = quoteEntity.getHeart();
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

	public String getIsPublish() {
		return isPublish;
	}

	public void setIsPublish(String isPublish) {
		this.isPublish = isPublish;
	}

	public Long getHeart() {
		return heart;
	}

	public void setHeart(Long heart) {
		this.heart = heart;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
