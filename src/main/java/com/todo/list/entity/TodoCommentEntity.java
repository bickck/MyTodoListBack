package com.todo.list.entity;

import java.sql.Timestamp;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity(name = "TODO_COMMENT_ENTITY")
public class TodoCommentEntity {

	@Id
	@Column(name = "TODO_COMMENT_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "TODO_COMMENT_UUID", unique = true)
	private String todoCommentUUID;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TODO_ID")
	private TodoEntity todo;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID")
	private UserEntity user;

	@Lob
	@Column(name = "COMMENT")
	private String comment;

	@CreationTimestamp
	@Column(name = "CREATETIMESTAMP")
	private Timestamp createTimestamp;

	@UpdateTimestamp
	@Column(name = "UPDATETIMESTAMP")
	private Timestamp updateTimestamp;

	public TodoCommentEntity() {
		// TODO Auto-generated constructor stub
	}

	public TodoCommentEntity(TodoEntity todo, UserEntity user, String comment) {
		super();
		this.todo = todo;
		this.user = user;
		this.comment = comment;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TodoEntity getTodo() {
		return todo;
	}

	public void setTodo(TodoEntity todo) {
		this.todo = todo;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Timestamp getCreateTimestamp() {
		return createTimestamp;
	}

	public void setCreateTimestamp(Timestamp createTimestamp) {
		this.createTimestamp = createTimestamp;
	}

	public Timestamp getUpdateTimestamp() {
		return updateTimestamp;
	}

	public void setUpdateTimestamp(Timestamp updateTimestamp) {
		this.updateTimestamp = updateTimestamp;
	}

}
