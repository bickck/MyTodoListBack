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
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.CreationTimestamp;

import com.todo.list.controller.dto.service.QuoteDTO;

@Entity(name = "USER_ENTITY")
public class UserEntity {

	@Id
	@Column(name = "USER_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "USERNAME")
	private String username;

	@Column(name = "PASSWORD")
	private String password;

	@Column(name = "CREATEDATE")
	@CreationTimestamp
	private Timestamp date;

	@BatchSize(size = 10)
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<UserQuoteEntity> quotes = new ArrayList<UserQuoteEntity>();

	@BatchSize(size = 10)
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<UserTodoEntity> todos = new ArrayList<UserTodoEntity>();

	public UserEntity() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "UserEntity [id=" + id + ", username=" + username + ", password=" + password + ", date=" + date + "]";
	}

	public UserEntity(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public UserEntity(Long id, String username, String password) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
	}

	public UserEntity(Long id, String username, String password, Timestamp date) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.date = date;
	}

	public UserEntity(String username, String password, List<UserQuoteEntity> quotes, List<UserTodoEntity> todos) {
		this.username = username;
		this.password = password;
		this.quotes = quotes;
		this.todos = todos;
	}

	public UserEntity(String username, String password, List<UserQuoteEntity> quotes) {
		this.username = username;
		this.password = password;
		this.quotes = quotes;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public List<UserQuoteEntity> getQuotes() {
		return quotes;
	}

	public void setQuotes(List<UserQuoteEntity> quotes) {
		this.quotes = quotes;
	}

	public List<UserTodoEntity> getTodos() {
		return todos;
	}

	public void setTodos(List<UserTodoEntity> todos) {
		this.todos = todos;
	}

}
