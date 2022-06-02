package com.todo.list.entity;

import java.sql.Timestamp;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.todo.list.controller.dto.service.TodoDTO;

@Entity(name = "USER_TODO_ENTITY")
public class UserTodoEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "USER_ID")
	private UserEntity user;

	@Column(name = "TITLE")
	private String title;

	@Lob
	@Column(name = "CONTENT")
	private String content;

	@CreationTimestamp
	@Column(name = "CREATE_DATE")
	private Timestamp createDate;

	@UpdateTimestamp
	@Column(name = "LAST_UPDATE")
	private Timestamp lastUpdate;

	private String tag;

	@Enumerated(value = EnumType.STRING)
	private Publish publish;

	public UserTodoEntity() {
		// TODO Auto-generated constructor stub
	}

	public UserTodoEntity(UserEntity user, String title, String content) {
		super();
		this.user = user;
		this.title = title;
		this.content = content;
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

	public Publish getPublish() {
		return publish;
	}

	public void setPublish(Publish publish) {
		this.publish = publish;
	}

}
