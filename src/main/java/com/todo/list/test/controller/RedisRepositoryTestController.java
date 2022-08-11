package com.todo.list.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.CacheProperties.Redis;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todo.list.entity.TodoEntity;
import com.todo.list.redis.RedisCacheManagerName;
import com.todo.list.redis.RedisCacheNames;
import com.todo.list.redis.RedisCacheable;
import com.todo.list.redis.service.TodoCacheService;
import com.todo.list.service.todo.UserTodoService;
import com.todo.list.test.Entity.EventEntity;
import com.todo.list.test.repository.EventRepository;

@RestController
public class RedisRepositoryTestController implements RedisCacheNames, RedisCacheManagerName {

	@Autowired
	private EventRepository eventRepository;

	long eventId = 1;

	@GetMapping(value = "/redis/event/save")
	public String redisEventSave() {
		eventRepository.save(new EventEntity(eventId));
		return "success";
	}

	@GetMapping(value = "/redis/event/find")
	public String redisEventSelect() {
		EventEntity entity = eventRepository.findById(eventId).get();
		System.out.println(entity.toString());
		return "success";
	}

	@RedisCacheable(value = "hi")
	@GetMapping(value = "/redis/annotation/cacheable")
	public String redisCacheableTest() {

		return "success";
	}
}
