package com.todo.list.repository.todo;

import com.todo.list.entity.TodoImageTempEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoImageTempRepository extends JpaRepository<TodoImageTempEntity , Long> {


    List<TodoImageTempEntity> findAllById(Long id);
}
