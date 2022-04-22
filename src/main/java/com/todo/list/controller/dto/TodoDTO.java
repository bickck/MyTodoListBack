package com.todo.list.controller.dto;

public class TodoDTO {

	private Long id;

	private String title;

	private String content;

	private String createDate;

	public TodoDTO(Long id, String title, String content, String createDate) {
		super();
		this.id = id;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

}
