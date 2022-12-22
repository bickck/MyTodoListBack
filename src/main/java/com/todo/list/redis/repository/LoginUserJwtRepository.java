package com.todo.list.redis.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.todo.list.redis.entity.LoginUserRedisEntity;

public interface LoginUserJwtRepository extends CrudRepository<LoginUserRedisEntity, String> {

	//Optional<LoginUserRedisEntity> findById(String id);

	List<LoginUserRedisEntity> findAll();
	
	void findTokenById(String payLoad);

}
