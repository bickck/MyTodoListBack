package com.todo.list.controller.builder;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.todo.list.controller.dto.user.UserDTO;
import com.todo.list.entity.QuoteEntity;
import com.todo.list.entity.TodoEntity;

public class UserBuilder {

	private Long id;

	private String username;

	private String password;

	private Timestamp date;

	private List<QuoteEntity> quotes = new ArrayList<QuoteEntity>();

	private List<TodoEntity> todos = new ArrayList<TodoEntity>();

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

	public UserBuilder setQuotes(List<QuoteEntity> entities) {
		this.quotes = entities;
		return this;
	}

	public UserBuilder setTodos(List<TodoEntity> entities) {
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
