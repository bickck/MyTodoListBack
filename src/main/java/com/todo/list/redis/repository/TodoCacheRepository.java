package com.todo.list.redis.repository;

import org.springframework.data.repository.CrudRepository;

import com.todo.list.redis.entity.RedisTodoEntity;

public interface TodoCacheRepository extends CrudRepository<RedisTodoEntity, Long>{

}
