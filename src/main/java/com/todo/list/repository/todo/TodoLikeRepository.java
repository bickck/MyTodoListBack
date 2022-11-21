package com.todo.list.repository.todo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;

import com.todo.list.entity.TodoEntity;
import com.todo.list.repository.mapper.TodoMapper;

public interface TodoLikeRepository extends JpaRepository<TodoEntity, Long> {

	@Query(value = 
			"SELECT t.TODO_ID AS id, t.TITLE AS title, t.CONTENT  AS CONTENT, u.USERNAME AS USERNAME, t.CREATETIMESTAMP AS CREATETIMESTAMP, t.ISAVAILABLEPUBLISH as isPublish, "
			+ "(SELECT COUNT(sub_t_h.id) FROM TODO_HEART_ENTITY AS sub_t_h WHERE t.todo_id = sub_t_h.id) AS HEART, "
			+ "(SELECT COUNT(TODO_IMG.TODO_ID) FROM TODO_IMAGE_ENTITY AS TODO_IMG WHERE t.TODO_ID = TODO_IMG.TODO_ID AND todo_heart.todo_id = TODO_IMG.TODO_ID) AS POSTIMGCOUNT " 
			+ "FROM USER_TODO_ENTITY t "
			+ "INNER JOIN TODO_HEART_ENTITY AS todo_heart "
			+ "ON todo_heart.todo_id = t.todo_id "
			+ "INNER JOIN USER_ENTITY AS u "
			+ "ON todo_heart.user_id = u.user_id "
			+ "WHERE t.user_id = :id "
			+ "AND u.email = :email ", nativeQuery = true)
	Page<TodoMapper> findUserLikeTodoByUserIdAndEmail(Long id, String email, Pageable pageable);

}
