package com.todo.list.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.todo.list.entity.TodoEntity;

public interface TodoLikeRepository extends JpaRepository<TodoEntity, Long> {

	
}
