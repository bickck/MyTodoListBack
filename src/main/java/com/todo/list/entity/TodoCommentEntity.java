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

@Entity(name = "TODO_COMMENT_ENTITY")
public class TodoCommentEntity {

	@Id
	@Column(name = "TODO_COMMENT_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

//	@Column(name = "COMMENT_UUID", unique = true)
//	private String todoCommentUUID;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TODO_ID")
	private TodoEntity todo;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID")
	private UserEntity user;

	@Lob
	@Column(name = "COMMENT")
	private String comment;

	@Column(name = "CREATE_DATE")
	private Timestamp createTimeStamp;

	@Column(name = "UPDATE_DATE")
	private Timestamp updateTimeStamp;

	public TodoCommentEntity() {
		// TODO Auto-generated constructor stub
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

	public Timestamp getCreateTimeStamp() {
		return createTimeStamp;
	}

	public void setCreateTimeStamp(Timestamp createTimeStamp) {
		this.createTimeStamp = createTimeStamp;
	}

	public Timestamp getUpdateTimeStamp() {
		return updateTimeStamp;
	}

	public void setUpdateTimeStamp(Timestamp updateTimeStamp) {
		this.updateTimeStamp = updateTimeStamp;
	}

}
