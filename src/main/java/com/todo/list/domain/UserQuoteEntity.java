package com.todo.list.domain;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;

import com.todo.list.controller.dto.QuoteDTO;

@Entity(name = "USERQUOTE")
public class UserQuoteEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "USER")
	private String username;

	@Column(name = "QUETO")
	private String queto;

	@Column(name = "AUTHOR")
	private String author;

	@CreationTimestamp
	@Column(name = "CREATEDATE")
	private Timestamp createDate;

	public UserQuoteEntity() {
		// TODO Auto-generated constructor stub
	}

	public UserQuoteEntity(@NotNull String user, @NotNull String queto, @NotNull String author) {
		super();
		this.username = user;
		this.queto = queto;
		this.author = author;
	}

	public UserQuoteEntity(@NotNull String user, @NotNull QuoteDTO quoteDTO) {
		super();
		this.username = user;
		this.queto = quoteDTO.getQueto();
		this.author = quoteDTO.getAuthor();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUser() {
		return username;
	}

	public void setUser(String user) {
		this.username = user;
	}

	public String getQueto() {
		return queto;
	}

	public void setQueto(String queto) {
		this.queto = queto;
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
