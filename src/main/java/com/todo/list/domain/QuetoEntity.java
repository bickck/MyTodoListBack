package com.todo.list.domain;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

import com.todo.list.controller.dto.QuoteDTO;

@Entity
public class QuetoEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "QUETO")
	private String queto;

	@Column(name = "AUTHOR")
	private String author;

	@CreationTimestamp
	@Column(name = "CREATEDATE")
	private Timestamp createDate;

	public QuetoEntity() {
		// TODO Auto-generated constructor stub
	}

	public QuetoEntity(String queto, String author) {
		super();
		this.queto = queto;
		this.author = author;
	}

	public QuetoEntity(QuoteDTO quetoDTO) {
		this.queto = quetoDTO.getQueto();
		this.author = quetoDTO.getAuthor();
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
