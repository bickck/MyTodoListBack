package com.todo.list.redis.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import com.todo.list.entity.TodoEntity;

@RedisHash(value = "", timeToLive = 3000)
public class RedisTodoEntity {

	@Id
	private Long id;

	private TodoEntity todoEntity;

	public RedisTodoEntity() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TodoEntity getTodoEntity() {
		return todoEntity;
	}

	public void setTodoEntity(TodoEntity todoEntity) {
		this.todoEntity = todoEntity;
	}

}
