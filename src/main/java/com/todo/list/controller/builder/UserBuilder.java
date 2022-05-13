package com.todo.list.controller.builder;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.todo.list.controller.dto.UserDTO;
import com.todo.list.entity.UserQuoteEntity;
import com.todo.list.entity.UserTodoEntity;

public class UserBuilder {

	private Long id;

	private String username;

	private String password;

	private Timestamp date;

	private List<UserQuoteEntity> quotes = new ArrayList<UserQuoteEntity>();

	private List<UserTodoEntity> todos = new ArrayList<UserTodoEntity>();

	public UserBuilder setId(Long id) {
		this.id = id;
		return this;
	}

	public UserBuilder setUserName(String username) {
		this.username = username;
		return this;
	}

	public UserBuilder setDate(Timestamp timestamp) {
		this.date = timestamp;
		return this;
	}

	public UserBuilder setQuotes(List<UserQuoteEntity> entities) {
		this.quotes = entities;
		return this;
	}

	public UserBuilder setTodos(List<UserTodoEntity> entities) {
		this.todos = entities;
		return this;
	}
	
	public UserBuilder setPassword(String password) {
		this.password = password;
		return this;
	}

	public UserDTO dtoBuilder() {
		return new UserDTO(username, password);
	}
}
