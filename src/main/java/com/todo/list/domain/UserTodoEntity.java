package com.todo.list.domain;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

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
