package com.todo.list.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.todo.list.entity.TodoImageEntity;

public interface TodoImageRepository extends JpaRepository<TodoImageEntity, Long> {

}
