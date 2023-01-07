package com.todo.list.redis.repository;

import org.springframework.data.repository.CrudRepository;

import com.todo.list.redis.entity.MessageChannelEntity;

public interface MessageChannelRepository extends CrudRepository<MessageChannelEntity, Long> {

}
