package com.todo.list.controller.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.todo.list.entity.QuoteEntity;
import com.todo.list.entity.base.Publish;
import com.todo.list.util.validation.annotation.PublishType;
import com.todo.list.util.validation.group.QuoteAccessArgumentGroup;

public class QuoteDTO implements Serializable {

	private Long id;

	@NotNull(message = "QUOTE를 입력해주세요.", groups = { QuoteAccessArgumentGroup.class })
	@NotEmpty(message = "입력 조건이 맞지 않습니다.", groups = { QuoteAccessArgumentGroup.class })
	private String quote;

	@NotEmpty(message = "QUOTE를 입력해주세요.", groups = { QuoteAccessArgumentGroup.class })
	@NotNull(message = "입력 조건이 맞지 않습니다.", groups = { QuoteAccessArgumentGroup.class })
	private String author;

	@NotNull(groups = QuoteAccessArgumentGroup.class)
	@PublishType(groups = QuoteAccessArgumentGroup.class)
	private Publish isPublish;

	private String username;

	private Long heart;

	public QuoteDTO() {
		// TODO Auto-generated constructor stub
	}

	public QuoteDTO(String quote, String author) {
		super();
		this.quote = quote;
		this.author = author;
	}

	public QuoteDTO(Long id, String quote, String author) {
		this.id = id;
		this.quote = quote;
		this.author = author;
	}

	public QuoteDTO(String quote, String author, Publish isPublish, Long heart) {
		super();
		this.quote = quote;
		this.author = author;
		this.isPublish = isPublish;
		this.heart = heart;
	}

	public QuoteDTO(Long id, String quote, String author, Publish isPublish, String username, Long heart) {
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
		this.isPublish = quoteEntity.getIsPublish();
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

	public Publish getIsPublish() {
		return isPublish;
	}

	public void setIsPublish(Publish isPublish) {
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
