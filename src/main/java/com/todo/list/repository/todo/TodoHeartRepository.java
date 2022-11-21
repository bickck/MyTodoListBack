package com.todo.list.repository.todo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.todo.list.entity.TodoHeartEntity;

public interface TodoHeartRepository extends JpaRepository<TodoHeartEntity, Long> {

	// @Query(value = "",nativeQuery = true)
	// Long countTodoHeartById(Long id);
	
	void deleteByUuid(String uuid);

	boolean existsByTodoEntityIdAndUserId(Long todoid, Long userid);

	@Query(value = "SELECT h.TODO_UUID "
			+ "FROM TODO_HEART_ENTITY AS h WHERE h.TODO_ID = :todoId AND h.USER_ID = :userId"
			, nativeQuery = true)
	String findTodoUUID(Long todoId, Long userId);

}
