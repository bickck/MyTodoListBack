package com.todo.list.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.todo.list.entity.UserEntity;
import com.todo.list.entity.UserTodoEntity;

public interface UserTodoRepository extends JpaRepository<UserTodoEntity, Long> {

	
	List<UserTodoEntity> findAllByUser(UserEntity userEntity, Pageable pageable);
}
