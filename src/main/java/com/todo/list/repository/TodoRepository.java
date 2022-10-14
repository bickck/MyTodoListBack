package com.todo.list.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.todo.list.entity.UserEntity;
import com.todo.list.entity.base.Publish;
import com.todo.list.entity.TodoEntity;

public interface TodoRepository extends JpaRepository<TodoEntity, Long> {

	TodoEntity findTodoEntityById(Long id);

	Page<TodoEntity> findTodoEntitiesByUserId(Long id, Pageable pageable);

	Page<TodoEntity> findTodoEntitiesByIsPublishOrderByIdDesc(Publish publish, Pageable pageable);
	
//	@Query(value = "")
//	Page<TodoEntity> findAllIsPublishOrderByIdDesc(Publish publish, Pageable pageable);

	List<TodoEntity> findAllEntitiesByIsPublish(Publish publish);

//   Page<TodoEntity> findTodoEntitiesByIsPublish(Publish publish, Pageable pageable);

//	List<TodoEntity> findAllByUserEntity(UserEntity userEntity, Pageable pageable);

}
