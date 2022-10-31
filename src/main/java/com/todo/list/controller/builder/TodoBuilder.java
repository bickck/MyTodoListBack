package com.todo.list.controller.builder;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.todo.list.entity.UserEntity;
import com.todo.list.controller.dto.TodoDTO;
import com.todo.list.entity.TodoEntity;

public class TodoBuilder {

	private Long id;

	private String username;

	private String title;

	private String content;

	private Timestamp createDate;

	public TodoBuilder setId(Long id) {
		this.id = id;
		return this;
	}

	public TodoBuilder setUsername(String username) {
		this.username = username;
		return this;
	}

	public TodoBuilder setTitle(String title) {
		this.title = title;
		return this;
	}

	public TodoBuilder setContent(String content) {
		this.content = content;
		return this;
	}

	public TodoBuilder setDate(Timestamp date) {
		this.createDate = date;
		return this;
	}

	public TodoDTO builder() {
		return new TodoDTO(id, username, title, content, createDate);
	}

//	public List<TodoDTO> listBuilder(List<TodoEntity> list) {
//		List<TodoDTO> todoDTOs = new ArrayList<TodoDTO>();
//		Iterator<TodoEntity> iterator = list.iterator();
//		while (iterator.hasNext()) {
//			todoDTOs.add(new TodoDTO(iterator.next().getId(), iterator.next().getUser().getUsername(),
//					iterator.next().getTitle(), iterator.next().getContent(), iterator.next().getCreateTimeStamp()));
//		}
//		return todoDTOs;
//	}

}
