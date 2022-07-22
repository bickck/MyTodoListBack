package com.todo.list.test.repository;

import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.repository.CrudRepository;

import com.todo.list.test.Entity.EventEntity;


public interface EventRepository extends CrudRepository<EventEntity, Long>{

}
