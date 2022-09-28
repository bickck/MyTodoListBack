package com.todo.list.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.todo.list.controller.dto.service.QuoteDTO;
import com.todo.list.entity.base.Publish;
import com.todo.list.entity.base.UserTimeStamp;

@Entity(name = "USER_QUOTE_ENTITY")
public class QuoteEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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

	@Column(name = "ISAVAILABLEPUBLISH")
	@Enumerated(value = EnumType.STRING)
	private Publish isPublish;

	@Column(name = "RECOMMAND")
	private Long recommand;

	@CreationTimestamp
	@Column(name = "CREATETIMESTAMP")
	private Timestamp creaTimestamp;

	@UpdateTimestamp
	@Column(name = "UPDATETIMESTAMP")
	private Timestamp updateTimestamp;

	public QuoteEntity() {
		// TODO Auto-generated constructor stub
	}

	public QuoteEntity(@NotNull UserEntity user, @NotNull String quote, @NotNull String author) {
		super();
		this.user = user;
		this.quote = quote;
		this.author = author;
	}

	public QuoteEntity(@NotNull UserEntity user, @NotNull QuoteDTO quoteDTO) {
		super();
		this.user = user;
		this.quote = quoteDTO.getQuote();
		this.author = quoteDTO.getAuthor();
	}

	public QuoteEntity(@NotNull UserEntity user, @NotNull String quote, @NotNull String author,
			@NotNull Publish isPublish, @NotNull Long recommand) {
		super();
		this.user = user;
		this.quote = quote;
		this.author = author;
		this.isPublish = isPublish;
		this.recommand = recommand;
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

	public Publish getIsPublish() {
		return isPublish;
	}

	public void setIsPublish(Publish isPublish) {
		this.isPublish = isPublish;
	}

	public Long getRecommand() {
		return recommand;
	}

	public void setRecommand(Long recommand) {
		this.recommand = recommand;
	}

	public Timestamp getCreaTimestamp() {
		return creaTimestamp;
	}

	public void setCreaTimestamp(Timestamp creaTimestamp) {
		this.creaTimestamp = creaTimestamp;
	}

	public Timestamp getUpdateTimestamp() {
		return updateTimestamp;
	}

	public void setUpdateTimestamp(Timestamp updateTimestamp) {
		this.updateTimestamp = updateTimestamp;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
