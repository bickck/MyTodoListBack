package com.todo.list.entity;

import java.sql.Timestamp;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.todo.list.controller.dto.service.TodoDTO;
import com.todo.list.entity.base.Publish;
import com.todo.list.entity.base.UserTimeStamp;

@Entity(name = "USER_TODO_ENTITY")
public class UserTodoEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

//	@Column(name = "TODO_UUID", unique = true)
//	private String todoUUID;

	@ManyToOne
	@JoinColumn(name = "USER_ID", nullable = false)
	private UserEntity user;

	@Column(name = "TITLE", nullable = false)
	private String title;

	@Lob
	@Column(name = "CONTENT", nullable = false)
	private String content;

	@Column(name = "HEART")
	private Long heart;

//	@Column(name = "COMMENT")
//	private List<TodoCommentEntity> comments;

	// private String tag;

	@Column(name = "ISAVAILABLEPUBLISH", nullable = false)
	@Enumerated(value = EnumType.STRING)
	private Publish isPublish;

//	@Embedded
//	private UserTimeStamp stamp;

	@CreationTimestamp
	@Column(name = "CREATE_DATE")
	private Timestamp createDate;

	@UpdateTimestamp
	@Column(name = "LAST_UPDATE")
	private Timestamp lastUpdate;

	public UserTodoEntity() {
		// TODO Auto-generated constructor stub
	}

	public UserTodoEntity(String title, String content) {
		super();
		this.title = title;
		this.content = content;
	}

	public UserTodoEntity(String title, String content, Publish publish) {
		super();
		this.title = title;
		this.content = content;
		this.isPublish = publish;
	}

	public UserTodoEntity(UserEntity user, String title, String content) {
		super();
		this.user = user;
		this.title = title;
		this.content = content;
	}

	public UserTodoEntity(UserEntity userEntity, String title, String content, Long heart, Publish publish) {
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

	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	public Timestamp getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

}
