package com.todo.list.repository.todo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.todo.list.entity.TodoCommentEntity;
import com.todo.list.repository.mapper.TodoCommentMapper;

public interface TodoCommentRepository extends JpaRepository<TodoCommentEntity, Long>{

	
	@Query(value ="SELECT TODO_COMMENT.id AS id, TODO_COMMENT.user.username AS username, "
			+ "TODO_COMMENT.comment AS comment, TODO_COMMENT.createTimestamp AS createTimeStamp "
			+ "FROM TODO_COMMENT_ENTITY AS TODO_COMMENT "
			+ "WHERE TODO_COMMENT.todo.id = :id")
	Page<TodoCommentMapper> findTodoCommentByTodoId(Long id,Pageable pageable);

}
