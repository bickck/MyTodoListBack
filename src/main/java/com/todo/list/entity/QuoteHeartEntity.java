package com.todo.list.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

@Entity(name = "QUOTE_HEART_ENTITY")
public class QuoteHeartEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "QUOTE_UUID", nullable = false)
	private String uuid;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID", nullable = false)
	private UserEntity user;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "QUOTE_ID", nullable = false)
	private QuoteEntity quoteEntity;

	@Column(name = "CREATETIMESTAMP")
	@CreationTimestamp
	private Timestamp createTimestamp;

	public QuoteHeartEntity() {
		// TODO Auto-generated constructor stub
	}

	public QuoteHeartEntity(String uuid, UserEntity user, QuoteEntity quoteEntity) {
		super();
		this.uuid = uuid;
		this.user = user;
		this.quoteEntity = quoteEntity;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public QuoteEntity getQuoteEntity() {
		return quoteEntity;
	}

	public void setQuoteEntity(QuoteEntity quoteEntity) {
		this.quoteEntity = quoteEntity;
	}

	public Timestamp getCreateTimestamp() {
		return createTimestamp;
	}

	public void setCreateTimestamp(Timestamp createTimestamp) {
		this.createTimestamp = createTimestamp;
	}

}
