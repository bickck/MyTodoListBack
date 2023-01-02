package com.todo.list.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class MessageEntity {

	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "com.todo.list.util.uuid.MessageUUIDGenerator")
	@Column(name = "MESSAGE_ID", columnDefinition = "varchar(32)")
	private String uuid;

	@Column(name = "USER_ID", nullable = false)
	private Long userId;

	@Column(name = "MESSAGE_TO", nullable = false)
	private String to;

	@Column(name = "MESSAGE_FROM", nullable = false)
	private String from;

	@Column(name = "MESSAGE", nullable = false)
	private String message;

	// private String messageExpired;

	public MessageEntity() {
		// TODO Auto-generated constructor stub
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public Long getUserid() {
		return userId;
	}

	public void setUserid(Long userid) {
		this.userId = userid;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

//	public String getMessageExpired() {
//		return messageExpired;
//	}
//
//	public void setMessageExpired(String messageExpired) {
//		this.messageExpired = messageExpired;
//	}

}
