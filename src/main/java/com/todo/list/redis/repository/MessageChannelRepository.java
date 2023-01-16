package com.todo.list.redis.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.todo.list.redis.entity.MessageChannelEntity;

@Repository
public interface MessageChannelRepository extends CrudRepository<MessageChannelEntity, Long> {

	List<MessageChannelEntity> findAll();
}
