package com.todo.list.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.todo.list.entity.Publish;
import com.todo.list.entity.UserEntity;
import com.todo.list.entity.TodoEntity;

public interface TodoRepository extends JpaRepository<TodoEntity, Long> {

	TodoEntity findTodoEntityById(Long id);

//	List<TodoEntity> findAllByUserEntity(UserEntity userEntity, Pageable pageable);

	Page<TodoEntity> findTodoEntitiesByUserId(Long id, Pageable pageable);

	Page<TodoEntity> findTodoEntitiesByIsPublishOrderByIdDesc(Publish publish, Pageable pageable);

	List<TodoEntity> findAllEntitiesByIsPublish(Publish publish);

	// Page<TodoEntity> findTodoEntitiesByIsPublish(Publish publish, Pageable
	// pageable);
}
