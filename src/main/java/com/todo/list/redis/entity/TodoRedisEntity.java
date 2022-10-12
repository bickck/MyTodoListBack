package com.todo.list.redis.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import com.todo.list.entity.UserTodoEntity;

@RedisHash(value = "", timeToLive = 3000)
public class TodoRedisEntity {

	@Id
	private Long id;

	private UserTodoEntity todoEntity;
	
	private Long expire;

	public TodoRedisEntity() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UserTodoEntity getTodoEntity() {
		return todoEntity;
	}

	public void setTodoEntity(UserTodoEntity todoEntity) {
		this.todoEntity = todoEntity;
	}

}
