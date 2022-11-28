package com.todo.list.repository.image;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.todo.list.entity.TodoEntity;
import com.todo.list.entity.TodoImageEntity;
import com.todo.list.repository.mapper.ImageMapper;

public interface TodoImageRepository extends JpaRepository<TodoImageEntity, Long> {

	@Query(value = "SELECT i.id AS id, i.originalFileName AS originalFileName, i.fileName AS fileName, i.filePath AS filePath "
			+ "FROM TODO_IMAGE_ENTITY i " + "WHERE todoBoard.id = :todoId")
	List<ImageMapper> findTodoImageById(Long todoId);

	@Query(value = "SELECT i.filePath AS FILEPATH " + "FROM TODO_IMAGE_ENTITY i "
			+ "WHERE i.originalFileName = :originalFileName AND i.fileName = :fileName")
	String findFilePathByOriginalFileNameAndFileName(String originalFileName, String fileName);

	boolean existsByTodoBoard(TodoEntity id);

	List<TodoImageEntity> findTodoImageEntityByTodoBoard(TodoEntity id);
}
