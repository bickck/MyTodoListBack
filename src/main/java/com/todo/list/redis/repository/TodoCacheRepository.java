package com.todo.list.redis.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.todo.list.redis.entity.TodoRedisEntity;

public interface TodoCacheRepository extends CrudRepository<TodoRedisEntity, Long>{

	public void findAll(Pageable pageable);
}
