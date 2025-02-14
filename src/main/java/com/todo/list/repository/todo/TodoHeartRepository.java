package com.todo.list.repository.todo;

import org.hibernate.annotations.SQLDeleteAll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.todo.list.entity.TodoEntity;
import com.todo.list.entity.TodoHeartEntity;

public interface TodoHeartRepository extends JpaRepository<TodoHeartEntity, Long> {

	// @Query(value = "",nativeQuery = true)
	// Long countTodoHeartById(Long id);

	void deleteByUuid(String uuid);
	
	@Modifying
	@Query(value = "DELETE FROM TODO_HEART_ENTITY "
			+ "WHERE TODO_ID = :todoid",nativeQuery = true)
	void deleteAllByTodoId(@Param(value = "todoid")Long todoid);

	boolean existsByTodoEntityIdAndUserId(Long todoid, Long userid);

	@Query(value = "SELECT h.TODO_UUID "
			+ "FROM TODO_HEART_ENTITY AS h "
			+ "WHERE h.TODO_ID = :todoId AND h.USER_ID = :userId", nativeQuery = true)
	String findTodoUUID(Long todoId, Long userId);

}
