package com.todo.list.repository.todo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;

import com.todo.list.entity.TodoEntity;
import com.todo.list.repository.mapper.TodoMapper;

public interface TodoLikeRepository extends JpaRepository<TodoEntity, Long> {

	@Query(value = 
			"SELECT distinct TODO.TODO_ID AS ID, TODO.TITLE, TODO.CONTENT, TODO.ISAVAILABLEPUBLISH AS ISPUBLISH, TODO.CREATETIMESTAMP AS CREATETIMESTAMP, U.USERNAME AS USERNAME, "
			+ "(SELECT COUNT(*) FROM TODO_COMMENT_ENTITY WHERE TODO.USER_ID = U.USER_ID) AS COMMENT ,"
			+ "(SELECT COUNT(*) FROM TODO_HEART_ENTITY AS sub_t_h WHERE TODO.todo_id = sub_t_h.todo_id) AS HEART, "
			+ "(SELECT COUNT(*) FROM TODO_IMAGE_ENTITY AS TODO_IMG WHERE TODO.TODO_ID = TODO_IMG.TODO_ID) AS POSTIMGCOUNT, "
			+ "(SELECT COUNT(*) FROM USER_IMAGE_ENTITY AS USER_IMG WHERE USER_IMG.USER_ID = U.USER_ID) AS USERIMGCOUNT "
			+ "FROM USER_TODO_ENTITY AS TODO " 
			+ "LEFT JOIN TODO_HEART_ENTITY AS T_H ON T_H.TODO_ID = TODO.TODO_ID "
			+ "LEFT JOIN TODO_IMAGE_ENTITY AS TODO_IMG ON TODO.TODO_ID = TODO_IMG.TODO_ID "
			+ "LEFT JOIN USER_ENTITY AS U ON U.USER_ID = TODO.USER_ID "
			+ "WHERE T_H.USER_ID = :userid ORDER BY DATE(TODO.CREATETIMESTAMP) DESC, TODO.CREATETIMESTAMP ASC ", nativeQuery = true)
	Page<TodoMapper> findUserLikeTodoByUserId(@Param("userid") Long id, Pageable pageable);
	
	
//	@Query(value = 
//			"SELECT distinct TODO.TODO_ID AS ID, TODO.TITLE, TODO.CONTENT, TODO.ISAVAILABLEPUBLISH AS ISPUBLISH, TODO.CREATETIMESTAMP AS CREATETIMESTAMP, U.USERNAME AS USERNAME, "
//			+ "(SELECT COUNT(*) FROM TODO_COMMENT_ENTITY WHERE TODO.USER_ID = U.USER_ID) AS COMMENT ,"
//			+ "(SELECT COUNT(*) FROM TODO_HEART_ENTITY AS sub_t_h WHERE TODO.todo_id = sub_t_h.todo_id) AS HEART, "
//			+ "(SELECT COUNT(*) FROM TODO_IMAGE_ENTITY AS TODO_IMG WHERE TODO.TODO_ID = TODO_IMG.TODO_ID) AS POSTIMGCOUNT, "
//			+ "(SELECT COUNT(*) FROM USER_IMAGE_ENTITY AS USER_IMG WHERE USER_IMG.USER_ID = U.USER_ID) AS USERIMGCOUNT "
//			+ "FROM USER_TODO_ENTITY AS TODO " 
//			+ "LEFT JOIN TODO_HEART_ENTITY AS T_H ON T_H.TODO_ID = TODO.TODO_ID "
//			+ "LEFT JOIN TODO_IMAGE_ENTITY AS TODO_IMG ON TODO.TODO_ID = TODO_IMG.TODO_ID "
//			+ "LEFT JOIN USER_ENTITY AS U ON U.USER_ID = TODO.USER_ID "
//			+ "WHERE T_H.USER_ID = :userid ORDER BY DATE(TODO.CREATETIMESTAMP) DESC, TODO.CREATETIMESTAMP ASC ", nativeQuery = true)
//	Page<TodoMapper> findUserLikeTodoByUsername(@Param("username") String username, Pageable pageable);

}
