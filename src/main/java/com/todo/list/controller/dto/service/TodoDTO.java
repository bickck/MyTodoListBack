package com.todo.list.controller.dto.service;

import java.sql.Timestamp;

import com.todo.list.entity.UserEntity;

public class TodoDTO {

	private Long id;

	private UserEntity user;

	private String title;

	private String content;

	private Timestamp createDate;

	public TodoDTO(Long id, UserEntity user, String title, String content, Timestamp createDate) {
		super();
		this.id = id;
		this.user = user;
		this.title = title;
		this.content = content;
		this.createDate = createDate;
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

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

}
