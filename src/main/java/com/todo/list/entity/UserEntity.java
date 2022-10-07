package com.todo.list.entity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.todo.list.controller.dto.service.QuoteDTO;

@Entity(name = "USER_ENTITY")
public class UserEntity {

	@Id
	@Column(name = "USER_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "EMAIL", unique = true)
	private String email;

	@Column(name = "USERNAME")
	private String username;

	@Column(name = "PASSWORD")
	private String password;

	@Column(name = "INTRO_COMMENT", length = 100)
	private String introComment;

	@Column(name = "CREATEDATE")
	@CreationTimestamp
	private Timestamp createDate;

	@Column(name = "UPDATECREATE")
	@UpdateTimestamp
	private Timestamp updateDate;

	@BatchSize(size = 10)
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<QuoteEntity> quotes = new ArrayList<QuoteEntity>();

	@BatchSize(size = 10)
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<TodoEntity> todos = new ArrayList<TodoEntity>();

//	@BatchSize(size = 10)
	@OneToOne(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private UserImageEntity userImageEntity;

	public UserEntity() {
		// TODO Auto-generated constructor stub
	}

	public UserEntity(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}

	public UserEntity(String email, String username, String password) {
		super();
		this.email = email;
		this.username = username;
		this.password = password;
	}

	public UserEntity(Long id, String username, String password) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
	}

	public UserEntity(String username, String password, List<QuoteEntity> quotes) {
		this.username = username;
		this.password = password;
		this.quotes = quotes;
	}

	public UserEntity(String username, String password, List<QuoteEntity> quotes, List<TodoEntity> todos) {
		this.username = username;
		this.password = password;
		this.quotes = quotes;
		this.todos = todos;
	}

	public UserEntity(String username, String password, String introComment, UserImageEntity userImageEntity) {
		this.username = username;
		this.password = password;
		this.introComment = introComment;
		this.userImageEntity = userImageEntity;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIntroComment() {
		return introComment;
	}

	public void setIntroComment(String introComment) {
		this.introComment = introComment;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<QuoteEntity> getQuotes() {
		return quotes;
	}

	public void setQuotes(List<QuoteEntity> quotes) {
		this.quotes = quotes;
	}

	public List<TodoEntity> getTodos() {
		return todos;
	}

	public void setTodos(List<TodoEntity> todos) {
		this.todos = todos;
	}

	public UserImageEntity getUserImageEntity() {
		return userImageEntity;
	}

	public void setUserImageEntity(UserImageEntity userImageEntity) {
		this.userImageEntity = userImageEntity;
	}

	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	public Timestamp getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Timestamp updateDate) {
		this.updateDate = updateDate;
	}

}
