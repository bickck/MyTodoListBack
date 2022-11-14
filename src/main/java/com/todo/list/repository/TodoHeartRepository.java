package com.todo.list.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.todo.list.entity.TodoHeartEntity;

public interface TodoHeartRepository extends JpaRepository<TodoHeartEntity, Long>{

	
	@Query(value = "",nativeQuery = true)
	Long countTodoHeartById(Long id);
}
