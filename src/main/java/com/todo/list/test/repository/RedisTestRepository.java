package com.todo.list.test.repository;

import org.springframework.data.repository.CrudRepository;

import com.todo.list.test.Entity.RedisTestEntity;

public interface RedisTestRepository extends CrudRepository<RedisTestEntity, Long> {

}
