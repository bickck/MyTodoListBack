package com.todo.list.entity;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;

import com.todo.list.controller.dto.QuoteDTO;

@Entity(name = "USER_QUOTE_ENTITY")
public class UserQuoteEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID")
	private UserEntity user;

	@Column(name = "QUOTE")
	private String quote;

	@Column(name = "AUTHOR")
	private String author;

	@CreationTimestamp
	@Column(name = "CREATEDATE")
	private Timestamp createDate;

	public UserQuoteEntity() {
		// TODO Auto-generated constructor stub
	}

	public UserQuoteEntity(@NotNull UserEntity user, @NotNull String quote, @NotNull String author) {
		super();
		this.user = user;
		this.quote = quote;
		this.author = author;
	}

	public UserQuoteEntity(@NotNull UserEntity user, @NotNull QuoteDTO quoteDTO) {
		super();
		this.user = user;
		this.quote = quoteDTO.getQuote();
		this.author = quoteDTO.getAuthor();
	}

	@Override
	public String toString() {
		return "UserQuoteEntity [id=" + id + ", user=" + user + ", quote=" + quote + ", author=" + author
				+ ", createDate=" + createDate + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
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
