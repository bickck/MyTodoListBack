package com.todo.list.entity.base;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

import com.todo.list.controller.dto.service.QuoteDTO;

@Entity(name="DEFAULTQUOTE")
public class AdminQuoteEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "QUOTE")
	private String quote;

	@Column(name = "AUTHOR")
	private String author;

	@CreationTimestamp
	@Column(name = "CREATEDATE")
	private Timestamp createDate;

	public AdminQuoteEntity() {
		// TODO Auto-generated constructor stub
	}

	public AdminQuoteEntity(String quote, String author) {
		super();
		this.quote = quote;
		this.author = author;
	}

	public AdminQuoteEntity(QuoteDTO quoteDTO) {
		this.quote = quoteDTO.getQuote();
		this.author = quoteDTO.getAuthor();
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

	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

}
