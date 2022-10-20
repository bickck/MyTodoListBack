package com.todo.list.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.todo.list.entity.UserEntity;
import com.todo.list.entity.base.Publish;
import com.todo.list.controller.dto.PostInterface;
import com.todo.list.controller.dto.TodoDTO;
import com.todo.list.entity.TodoEntity;

public interface TodoRepository extends JpaRepository<TodoEntity, Long> {

	TodoEntity findTodoEntityById(Long id);

	Page<TodoEntity> findTodoEntitiesByUserId(Long id, Pageable pageable);

	Page<TodoEntity> findTodoEntitiesByIsPublishOrderByIdDesc(Publish publish, Pageable pageable);

	@Query(value = "SELECT TODO.TODO_ID AS ID, U.USERNAME, TODO.TITLE, TODO.CONTENT, TODO.ISAVAILABLEPUBLISH AS ISPUBLISH, TODO.HEART, "
			+ "(SELECT COUNT(id) FROM TODO_COMMENT_ENTITY WHERE TODO.USER_ID = U.USER_ID) AS COMMENT "
			+ "FROM USER_TODO_ENTITY AS TODO "
			+ "LEFT JOIN USER_ENTITY AS U ON TODO.USER_ID = U.USER_ID "
			+ "WHERE ISAVAILABLEPUBLISH = 'PUBLISH'", 
			countQuery = "SELECT COUNT(*) FROM USER_TODO_ENTITY WHERE ISAVAILABLEPUBLISH = isPublish"
			,nativeQuery = true)
	Page<PostInterface> findTodoMainPostByPublish(@Param("isPublish") Publish publish,Pageable pageable);

	List<TodoEntity> findAllEntitiesByIsPublish(Publish publish);
	
//	@Query(value = "SELECT TODO.TODO_ID AS ID, U.USERNAME, TODO.TITLE, TODO.CONTENT, TODO.ISAVAILABLEPUBLISH AS ISPUBLISH, TODO.HEART, "
//	+ "(SELECT COUNT(id) FROM TODO_COMMENT_ENTITY WHERE TODO.USER_ID = U.USER_ID) AS COMMENT "
//	+ "FROM USER_TODO_ENTITY AS TODO "
//	+ "LEFT JOIN USER_ENTITY AS U ON TODO.USER_ID = U.USER_ID "
//	+ "WHERE ISAVAILABLEPUBLISH = 'PUBLISH'", 
////	countQuery = "SELECT COUNT(*) FROM USER_TODO_ENTITY WHERE ISAVAILABLEPUBLISH = isPublish",
//	nativeQuery = true)
//Page<PostInterface> findTodoMainPostNonPage();

//	@Query(value = "")
//	Page<TodoEntity> findAllIsPublishOrderByIdDesc(Publish publish, Pageable pageable);

//   Page<TodoEntity> findTodoEntitiesByIsPublish(Publish publish, Pageable pageable);

//	List<TodoEntity> findAllByUserEntity(UserEntity userEntity, Pageable pageable);

}
