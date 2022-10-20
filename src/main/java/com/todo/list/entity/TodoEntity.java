package com.todo.list.entity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.todo.list.controller.dto.TodoDTO;
import com.todo.list.entity.base.Publish;
import com.todo.list.entity.base.UserTimeStamp;

@Entity(name = "USER_TODO_ENTITY")
public class TodoEntity {

	@Id
	@Column(name = "TODO_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

//	@Column(name = "TODO_UUID", unique = true)
//	private String todoUUID;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID", nullable = false)
	private UserEntity user;

	@Column(name = "TITLE", nullable = false)
	private String title;

	@Lob
	@Column(name = "CONTENT", nullable = false)
	private String content;

	@Column(name = "HEART")
	private Long heart;

	@OneToMany(mappedBy = "user")
	@Column(name = "COMMENT")
	private List<TodoCommentEntity> comments = new ArrayList<TodoCommentEntity>();

	@OneToMany(mappedBy = "todoBoard")
	@Column(name = "TODO_IMAGE")
	private List<TodoImageEntity> todoImages = new ArrayList<TodoImageEntity>();

	@Column(name = "ISAVAILABLEPUBLISH", nullable = false)
	@Enumerated(value = EnumType.STRING)
	private Publish isPublish;

	@CreationTimestamp
	@Column(name = "CREATE_DATE")
	private Timestamp createTimeStamp;

	@UpdateTimestamp
	@Column(name = "UPDATE_DATE")
	private Timestamp updateTimeStamp;

	public TodoEntity() {
		// TODO Auto-generated constructor stub
	}

	public TodoEntity(String title, String content) {
		super();
		this.title = title;
		this.content = content;
	}

	public TodoEntity(String title, String content, Publish publish) {
		super();
		this.title = title;
		this.content = content;
		this.isPublish = publish;
	}

	public TodoEntity(UserEntity user, String title, String content) {
		super();
		this.user = user;
		this.title = title;
		this.content = content;
	}

	public TodoEntity(UserEntity userEntity, String title, String content, Long heart, Publish publish) {
		super();
		this.user = userEntity;
		this.title = title;
		this.content = content;
		this.heart = heart;
		this.isPublish = publish;
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

	public Long getHeart() {
		return heart;
	}

	public void setHeart(Long heart) {
		this.heart = heart;
	}

	public Publish getIsPublish() {
		return isPublish;
	}

	public void setIsPublish(Publish isPublish) {
		this.isPublish = isPublish;
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

	public List<TodoCommentEntity> getComments() {
		return comments;
	}

	public void setComments(List<TodoCommentEntity> comments) {
		this.comments = comments;
	}

	public List<TodoImageEntity> getTodoImages() {
		return todoImages;
	}

	public void setTodoImages(List<TodoImageEntity> todoImages) {
		this.todoImages = todoImages;
	}

}
