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

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.todo.list.controller.dto.QuoteDTO;
import com.todo.list.entity.base.Publish;
import com.todo.list.entity.base.UserTimeStamp;

@Entity(name = "USER_QUOTE_ENTITY")
public class QuoteEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "QUOTE_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID", nullable = false)
	private UserEntity user;

	@Column(name = "QUOTE", nullable = false)
	private String quote;

	@Column(name = "AUTHOR", nullable = false)
	private String author;

//	@ColumnDefault(value = "PUBLISH")
	@Column(name = "ISAVAILABLEPUBLISH", nullable = false)
	@Enumerated(value = EnumType.STRING)
	private Publish isPublish;

//	@ColumnDefault(value = "0")
	@Column(name = "HEART")
	private Long heart;

	@CreationTimestamp
	@Column(name = "CREATETIMESTAMP")
	private Timestamp createTimestamp;

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

	public QuoteEntity(UserEntity user, String quote, String author, Publish isPublish, Long heart) {
		super();
		this.user = user;
		this.quote = quote;
		this.author = author;
		this.isPublish = isPublish;
		this.heart = heart;
	}

	public QuoteEntity(Long id, UserEntity user, String quote, String author, Long heart, Timestamp createTimestamp) {
		super();
		this.id = id;
		this.user = user;
		this.quote = quote;
		this.author = author;
		this.heart = heart;
		this.createTimestamp = createTimestamp;
	}

	public QuoteEntity(Long id, UserEntity user, String quote, String author, Publish isPublish, Long heart,
			Timestamp createTimestamp, Timestamp updateTimestamp) {
		super();
		this.id = id;
		this.user = user;
		this.quote = quote;
		this.author = author;
		this.isPublish = isPublish;
		this.heart = heart;
		this.createTimestamp = createTimestamp;
		this.updateTimestamp = updateTimestamp;
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

	public Long getHeart() {
		return heart;
	}

	public void setHeart(Long heart) {
		this.heart = heart;
	}

	public Timestamp getCreateTimestamp() {
		return createTimestamp;
	}

	public void setCreateTimestamp(Timestamp createTimestamp) {
		this.createTimestamp = createTimestamp;
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
