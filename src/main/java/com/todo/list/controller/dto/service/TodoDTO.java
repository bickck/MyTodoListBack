package com.todo.list.controller.dto.service;

import java.io.Serializable;
import java.sql.Timestamp;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.todo.list.entity.UserEntity;

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

	private String title;

	private String content;

	private String isChekcPuhlic;

	private Timestamp createDate;

	public TodoDTO() {
		// TODO Auto-generated constructor stub
	}

	public TodoDTO(Long id, String user, String title, String content, Timestamp createDate) {
		super();
		this.id = id;
		this.user = user;
		this.title = title;
		this.content = content;
		this.createDate = createDate;
	}

	public TodoDTO(Long id, String user, String title, String content, String isChekcPuhlic, Timestamp createDate) {
		super();
		this.id = id;
		this.user = user;
		this.title = title;
		this.content = content;
		this.isChekcPuhlic = isChekcPuhlic;
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

	public String getIsChekcPuhlic() {
		return isChekcPuhlic;
	}

	public void setIsChekcPuhlic(String isChekcPuhlic) {
		this.isChekcPuhlic = isChekcPuhlic;
	}

	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

}
