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

@Entity(name = "USERQUOTE")
public class UserQuoteEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "USERNAME")
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

	public UserQuoteEntity(@NotNull UserEntity username, String queto, String author) {
		super();
		this.username = username.getUsername();
		this.queto = queto;
		this.author = author;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
