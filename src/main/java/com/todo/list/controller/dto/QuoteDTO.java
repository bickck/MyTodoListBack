package com.todo.list.controller.dto;

import com.todo.list.domain.QuetoEntity;

import lombok.Data;
import lombok.Getter;


public class QuoteDTO {

	private Long id;
	private String quote;
	private String author;

	public QuoteDTO() {
		// TODO Auto-generated constructor stub
	}

	public QuoteDTO(String quote, String author) {
		super();
		this.quote = quote;
		this.author = author;
	}

	public QuoteDTO(Long id, QuetoEntity quote) {
		this.id = id;
		this.quote = quote.getQueto();
		this.author = quote.getAuthor();
	}

	public QuoteDTO(QuetoEntity queto) {
		this.id = queto.getId();
		this.quote = queto.getQueto();
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
