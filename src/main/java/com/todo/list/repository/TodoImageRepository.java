package com.todo.list.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.todo.list.entity.TodoImageEntity;
import com.todo.list.repository.mapper.ImageMapper;

public interface TodoImageRepository extends JpaRepository<TodoImageEntity, Long> {

	@Query(value="SELECT i.id AS id, i.originalFileName AS originalFileName, i.fileName AS fileName, i.filePath AS filePath "
			+ "FROM TODO_IMAGE_ENTITY i WHERE i.id = :id")
	List<ImageMapper> findTodoImageById(Long id);
}
