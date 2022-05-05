package com.todo.list.domain;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

import com.todo.list.controller.dto.TodoDTO;

@Entity(name = "TODO")
public class UserTodoEntity {

	@Id
	private Long id;

	@Column(name = "USER")
	private String user;

	@Column(name = "TITLE")
	private String title;

	@Column(name = "CONTENT")
	private String content;

	@CreationTimestamp
	@Column(name = "CREATE_DATE")
	private Timestamp date;

	public UserTodoEntity(String user, String title, String content) {
		super();
		this.user = user;
		this.title = title;
		this.content = content;
	}

	public UserTodoEntity(Long id, String user, String title, String content, Timestamp date) {
		super();
		this.id = id;
		this.user = user;
		this.title = title;
		this.content = content;
		this.date = date;
	}
	
	public UserTodoEntity(TodoDTO dto) {
		// TODO Auto-generated constructor stub
		this.id = dto.getId();
		this.user = dto.getUsername();
		this.title = dto.getTitle();
		this.content = dto.getContent();
		this.date = dto.getCreateDate();
	}

	@Override
	public String toString() {
		return "UserTodoEntity [id=" + id + ", user=" + user.toString() + ", title=" + title + ", content=" + content
				+ ", date=" + date + "]";

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return user;
	}

	public void setUsername(String user) {
		this.user = user;
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

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

}
