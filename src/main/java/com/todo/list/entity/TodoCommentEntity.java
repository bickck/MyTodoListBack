package com.todo.list.entity;

import java.sql.Timestamp;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "TODO_COMMENT_ENTITY")
public class TodoCommentEntity {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name ="COMMENT_UUID",unique = true)
	private String todoCommentUUID;

//	@Column(name = "USER_ID")
//	private UserEntity user;
	
//	@Column(name = "TODO_ID")
//	private String todoUUID;

	@Column(name = "COMMENT")
	private String comment;
	
	@Column(name = "CREATE_DATE")
	private Timestamp createTimeStamp;
	
	@Column(name = "UPDATE_DATE")
	private Timestamp updateTimeStamp;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

//	public UserEntity getUser() {
//		return user;
//	}
//
//	public void setUser(UserEntity user) {
//		this.user = user;
//	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}
