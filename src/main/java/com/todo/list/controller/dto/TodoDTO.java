package com.todo.list.controller.dto;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.todo.list.entity.UserEntity;
import com.todo.list.entity.base.Publish;
import com.todo.list.util.validation.annotation.PublishType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

public class TodoDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private String user;

	@Size(max = 1000, message = "Check max size")
	@NotEmpty(message = "Check your title")
	private String title;

	@NotEmpty(message = "Check your content")
	private String content;

	@PublishType
	@NotEmpty(message = "Check is published")
	private Publish isPublish;

	public TodoDTO() {
		// TODO Auto-generated constructor stub
	}

	public TodoDTO(Long id, String user, String title, String content) {
		super();
		this.id = id;
		this.user = user;
		this.title = title;
		this.content = content;
	}

	public TodoDTO(String user, String title, String content, Publish isPublish) {
		super();
		this.user = user;
		this.title = title;
		this.content = content;
		this.isPublish = isPublish;
	}

	public TodoDTO(Long id, String user, String title, String content, Publish isPublish) {
		super();
		this.id = id;
		this.user = user;
		this.title = title;
		this.content = content;
		this.isPublish = isPublish;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Publish getIsPublish() {
		return isPublish;
	}

	public void setIsPublish(Publish isPublish) {
		this.isPublish = isPublish;
	}
}
