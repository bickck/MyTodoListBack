package com.todo.list.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.todo.list.entity.UserTodoEntity;

public interface TodoRepository extends JpaRepository<UserTodoEntity, Long> {

}
