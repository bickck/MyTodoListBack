package com.todo.list.controller.dto;

import java.sql.Timestamp;

public class TodoDTO {

	private Long id;

	private String username;

	private String title;

	private String content;

	private Timestamp createDate;

	public TodoDTO(Long id, String username, String title, String content, Timestamp createDate) {
		super();
		this.id = id;
		this.username = username;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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
