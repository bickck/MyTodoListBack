package com.todo.list.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.todo.list.entity.UserEntity;
import com.todo.list.entity.base.Publish;
import com.todo.list.repository.mapper.TodoMapper;
import com.todo.list.controller.dto.TodoDTO;
import com.todo.list.entity.TodoEntity;

public interface TodoRepository extends JpaRepository<TodoEntity, Long> {

//	@Query(value = "SELECT T.id, T.title, T.content, T."
//			+ "T. FROM TODOENTITY T WHERE T.id := id")
	TodoEntity findTodoEntityById(Long id);

	Page<TodoEntity> findTodoEntitiesByUserId(Long id, Pageable pageable);

	Page<TodoEntity> findTodoEntitiesByIsPublishOrderByIdDesc(Publish publish, Pageable pageable);

	@Query(value = "SELECT TODO.TODO_ID AS ID, U.USERNAME, TODO.TITLE, TODO.CONTENT, TODO.ISAVAILABLEPUBLISH AS ISPUBLISH, TODO.HEART, "
			+ "(SELECT COUNT(id) FROM TODO_COMMENT_ENTITY WHERE TODO.USER_ID = U.USER_ID) AS COMMENT,"
			+ "TODO.CREATE_DATE AS CREATETIMESTAMP,"
			+ "(SELECT COUNT(id) FROM TODO_IMAGE_ENTITY AS TODO_IMG WHERE TODO.TODO_ID = TODO_IMG.TODO_ID) AS POSTIMGCOUNT " 
			+ "FROM USER_TODO_ENTITY AS TODO "
			+ "LEFT JOIN USER_ENTITY AS U ON TODO.USER_ID = U.USER_ID "
			+ "WHERE ISAVAILABLEPUBLISH = 'PUBLISH'", 
			countQuery = "SELECT COUNT(*) FROM USER_TODO_ENTITY WHERE ISAVAILABLEPUBLISH = isPublish"
			,nativeQuery = true)
	Page<TodoMapper> findTodoMainPostByPublish(@Param("isPublish") Publish publish,Pageable pageable);

	List<TodoEntity> findAllEntitiesByIsPublish(Publish publish);
	
//Page<PostInterface> findTodoMainPostNonPage();

//	@Query(value = "")
//	Page<TodoEntity> findAllIsPublishOrderByIdDesc(Publish publish, Pageable pageable);

//   Page<TodoEntity> findTodoEntitiesByIsPublish(Publish publish, Pageable pageable);

//	List<TodoEntity> findAllByUserEntity(UserEntity userEntity, Pageable pageable);

}
