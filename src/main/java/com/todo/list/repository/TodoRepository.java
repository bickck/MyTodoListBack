package com.todo.list.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.todo.list.entity.base.Publish;
import com.todo.list.repository.custom.TodoApiCustomRepository;
import com.todo.list.repository.mapper.TodoMapper;
import com.todo.list.entity.TodoEntity;

public interface TodoRepository extends JpaRepository<TodoEntity, Long>, TodoApiCustomRepository {

	@Query(value = "SELECT TODO.TODO_ID AS ID, U.USERNAME, TODO.TITLE, TODO.CONTENT, TODO.ISAVAILABLEPUBLISH AS ISPUBLISH, "
			+ "TODO.CREATETIMESTAMP AS CREATETIMESTAMP,"
			+ "(SELECT COUNT(sub_t_h.id) FROM TODO_HEART_ENTITY AS sub_t_h WHERE TODO.todo_id = sub_t_h.todo_id) AS HEART, "
			+ "(SELECT COUNT(id) FROM TODO_COMMENT_ENTITY WHERE TODO.USER_ID = U.USER_ID) AS COMMENT,"
			+ "(SELECT COUNT(id) FROM TODO_IMAGE_ENTITY AS TODO_IMG WHERE TODO.TODO_ID = TODO_IMG.TODO_ID) AS POSTIMGCOUNT, " 
			+ "(SELECT COUNT(id) FROM USER_IMAGE_ENTITY AS USER_IMG WHERE USER_IMG.USER_ID = U.USER_ID) AS USERIMGCOUNT " 
			+ "FROM USER_TODO_ENTITY AS TODO "
			+ "LEFT JOIN USER_ENTITY AS U ON TODO.USER_ID = U.USER_ID "
			+ "WHERE TODO.TODO_ID = :todoid", 
			nativeQuery = true)
	TodoMapper findTodoById(@Param("todoid")Long id);
	
	TodoEntity findTodoEntityById(Long id);


	Page<TodoEntity> findTodoEntitiesByUserId(Long id, Pageable pageable);

	Page<TodoEntity> findTodoEntitiesByIsPublishOrderByIdDesc(Publish publish, Pageable pageable);
	
	/**
	 * 
	 * 
	 * 
	 */
	
	@Query(value = 
			"SELECT TODO.TODO_ID AS ID, U.USERNAME, TODO.TITLE, TODO.CONTENT, TODO.ISAVAILABLEPUBLISH AS ISPUBLISH,"
			+ "TODO.CREATETIMESTAMP AS CREATETIMESTAMP, USER_IMAGE.USER_IMAGE_UUID AS UserImageUUID,"
			+ "(SELECT COUNT(sub_t_h.id) FROM TODO_HEART_ENTITY AS sub_t_h WHERE TODO.todo_id = sub_t_h.todo_id) AS HEART, "
			+ "(SELECT COUNT(id) FROM TODO_COMMENT_ENTITY WHERE TODO.USER_ID = U.USER_ID) AS COMMENT,"
			+ "(SELECT COUNT(id) FROM TODO_IMAGE_ENTITY AS TODO_IMG WHERE TODO.TODO_ID = TODO_IMG.TODO_ID) AS POSTIMGCOUNT " 
			+ "FROM USER_TODO_ENTITY AS TODO "
			+ "LEFT JOIN USER_ENTITY AS U ON TODO.USER_ID = U.USER_ID "
			+ "LEFT JOIN USER_IMAGE_ENTITY AS USER_IMAGE ON USER_IMAGE.USER_ID = U.USER_ID "
			+ "WHERE U.USER_ID = :userid "
			+ "AND U.EMAIL = :email ORDER BY TODO.TODO_ID DESC", 
			countQuery = "SELECT COUNT(*) FROM USER_TODO_ENTITY"
			,nativeQuery = true)
	Page<TodoMapper> findUserTodoByUserIdAndEmail(@Param("userid") Long userid,@Param("email") String email, Pageable pageable);
	
	/**
	 * 
	 * 
	 * 
	 */
	
	@Query(value = 
			"SELECT TODO.TODO_ID AS ID, U.USERNAME, TODO.TITLE, TODO.CONTENT, TODO.ISAVAILABLEPUBLISH AS ISPUBLISH,"
			+ "TODO.CREATETIMESTAMP AS CREATETIMESTAMP, USER_IMAGE.USER_IMAGE_UUID AS UserImageUUID,"
			+ "(SELECT COUNT(sub_t_h.id) FROM TODO_HEART_ENTITY AS sub_t_h WHERE TODO.todo_id = sub_t_h.todo_id) AS HEART, "
			+ "(SELECT COUNT(id) FROM TODO_COMMENT_ENTITY WHERE TODO.USER_ID = U.USER_ID) AS COMMENT,"
			+ "(SELECT COUNT(id) FROM TODO_IMAGE_ENTITY AS TODO_IMG WHERE TODO.TODO_ID = TODO_IMG.TODO_ID) AS POSTIMGCOUNT " 
			+ "FROM USER_TODO_ENTITY AS TODO "
			+ "LEFT JOIN USER_ENTITY AS U ON TODO.USER_ID = U.USER_ID "
			+ "LEFT JOIN USER_IMAGE_ENTITY AS USER_IMAGE ON USER_IMAGE.USER_ID = U.USER_ID "
			+ "WHERE U.USERNAME = :username ORDER BY TODO.TODO_ID DESC ", 
			countQuery = "SELECT COUNT(*) FROM USER_TODO_ENTITY"
			,nativeQuery = true)
	Page<TodoMapper> findUserTodoByUsername(@Param("username") String username, Pageable pageable);
	
	
	/**
	 * Todo 메인 쿼리문
	 * 
	 * @param publish
	 * @param pageable
	 * @return
	 */

	@Query(value = "SELECT TODO.TODO_ID AS ID, U.USERNAME, TODO.TITLE, TODO.CONTENT, TODO.ISAVAILABLEPUBLISH AS ISPUBLISH, "
			+ "TODO.CREATETIMESTAMP AS CREATETIMESTAMP, USER_IMAGE.USER_IMAGE_UUID AS UserImageUUID, "
			+ "(SELECT COUNT(sub_t_h.id) FROM TODO_HEART_ENTITY AS sub_t_h WHERE TODO.todo_id = sub_t_h.todo_id) AS HEART, "
			+ "(SELECT COUNT(id) FROM TODO_COMMENT_ENTITY AS TODO_COMMENT WHERE TODO_COMMENT.TODO_ID = TODO.TODO_ID) AS COMMENT,"
			+ "(SELECT COUNT(id) FROM TODO_IMAGE_ENTITY AS TODO_IMG WHERE TODO.TODO_ID = TODO_IMG.TODO_ID) AS POSTIMGCOUNT " 
			+ "FROM USER_TODO_ENTITY AS TODO "
			+ "LEFT JOIN USER_ENTITY AS U ON TODO.USER_ID = U.USER_ID "
			+ "LEFT JOIN USER_IMAGE_ENTITY AS USER_IMAGE ON USER_IMAGE.USER_ID = U.USER_ID "
			+ "WHERE ISAVAILABLEPUBLISH = 'PUBLISH' ORDER BY TODO.TODO_ID DESC ", 
			countQuery = "SELECT COUNT(*) FROM USER_TODO_ENTITY WHERE ISAVAILABLEPUBLISH = 'PUBLISH'"
			,nativeQuery = true)
	Page<TodoMapper> findTodoMainPostByPublish(@Param("isPublish") Publish publish, Pageable pageable);
	
	@Query(value = "SELECT TODO.TODO_ID AS ID, U.USERNAME, TODO.TITLE, TODO.CONTENT, TODO.ISAVAILABLEPUBLISH AS ISPUBLISH, TODO.HEART,"
			+ "(SELECT COUNT(id) FROM TODO_COMMENT_ENTITY WHERE TODO.USER_ID = U.USER_ID) AS COMMENT,"
			+ "TODO.CREATETIMESTAMP AS CREATETIMESTAMP,"
			+ "(SELECT COUNT(id) FROM TODO_IMAGE_ENTITY AS TODO_IMG WHERE TODO.TODO_ID = TODO_IMG.TODO_ID) AS POSTIMGCOUNT " 
			+ "FROM USER_TODO_ENTITY AS TODO "
			+ "LEFT JOIN USER_ENTITY AS U ON TODO.USER_ID = U.USER_ID "
			+ "WHERE ISAVAILABLEPUBLISH = 'PUBLISH' ORDER BY TODO.TODO_ID DESC", 
			countQuery = "SELECT COUNT(*) FROM USER_TODO_ENTITY WHERE ISAVAILABLEPUBLISH = 'PUBLISH'"
			,nativeQuery = true)
	Page<TodoMapper> findRecommandTodos(@Param("isPublish") Publish publish, Pageable pageable);
	
	@Query(value = "SELECT TODO.TODO_ID AS ID, U.USERNAME, TODO.TITLE, TODO.CONTENT, TODO.ISAVAILABLEPUBLISH AS ISPUBLISH, TODO.HEART,"
			+ "(SELECT COUNT(id) FROM TODO_COMMENT_ENTITY WHERE TODO.USER_ID = U.USER_ID) AS COMMENT,"
			+ "TODO.CREATETIMESTAMP AS CREATETIMESTAMP,"
			+ "(SELECT COUNT(id) FROM TODO_IMAGE_ENTITY AS TODO_IMG WHERE TODO.TODO_ID = TODO_IMG.TODO_ID) AS POSTIMGCOUNT " 
			+ "FROM USER_TODO_ENTITY AS TODO "
			+ "LEFT JOIN USER_ENTITY AS U ON TODO.USER_ID = U.USER_ID "
			+ "WHERE ISAVAILABLEPUBLISH = 'PUBLISH' ORDER BY TODO.TODO_ID DESC", 
			countQuery = "SELECT COUNT(*) FROM USER_TODO_ENTITY WHERE ISAVAILABLEPUBLISH = 'PUBLISH'"
			,nativeQuery = true)
	Page<TodoMapper> findDailyTodos(@Param("isPublish") Publish publish, Pageable pageable);

	List<TodoEntity> findAllEntitiesByIsPublish(Publish publish);

}
