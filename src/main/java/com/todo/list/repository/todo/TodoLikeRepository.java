package com.todo.list.repository.todo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;

import com.todo.list.entity.TodoEntity;
import com.todo.list.repository.mapper.TodoMapper;

public interface TodoLikeRepository extends JpaRepository<TodoEntity, Long> {

	@Query(value = "SELECT q.todo_id AS id, t.title AS title, t.content  AS content, u.username AS username, t.createTimestamp AS createTimestamp, t.isavailablepublish as isPublish, "
			+ "(SELECT COUNT(sub_t_h.id) FROM TODO_HEART_ENTITY AS sub_t_h WHERE t.todo_id = sub_t_h.id) AS HEART "
			+ "FROM USER_TODO_ENTITY t "
			+ "INNER JOIN TODO_HEART_ENTITY AS todo_heart "
			+ "ON todo_heart.todo_id = t.todo_id "
			+ "INNER JOIN USER_ENTITY AS u "
			+ "ON todo_heart.user_id = u.user_id "
			+ "WHERE t.user_id = :id "
			+ "AND u.email = :email ", nativeQuery = true)
	Page<TodoMapper> findUserLikeTodoByUserIdAndEmail(Long id, String email, Pageable pageable);

}
