package com.todo.list.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity(name="TODO_HEART_ENTITY")
public class TodoHeartEntity {

	@Id @Column(name = "TODO_HEART_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "TODO_HEART_UUID", nullable = false)
	private String uuid;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID", nullable = false)
	private UserEntity user;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TODO_ID", nullable = false)
	private TodoEntity todoEntity;

	@Column(name = "CREATETIMESTAMP")
	@CreationTimestamp
	private Timestamp createTimestamp;

	public TodoHeartEntity() {
		// TODO Auto-generated constructor stub
	}

	public TodoHeartEntity(String uuid, UserEntity user, TodoEntity todoEntity) {
		super();
		this.uuid = uuid;
		this.user = user;
		this.todoEntity = todoEntity;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public TodoEntity getTodoEntity() {
		return todoEntity;
	}

	public void setTodoEntity(TodoEntity todoEntity) {
		this.todoEntity = todoEntity;
	}

	public Timestamp getCreateTimestamp() {
		return createTimestamp;
	}

	public void setCreateTimestamp(Timestamp createTimestamp) {
		this.createTimestamp = createTimestamp;
	}

}
