package com.todo.list.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.todo.list.service.todo.UserTodoService;

@Service
public class TodoCacheService implements CacheStorageManager{

	@Autowired
	private RedisTemplate<Object, Object> redisTemplate;
	
	@Autowired
	private RedisCacheManager redisCacheManager;
	
	@Autowired
	private UserTodoService userTodoService;
	
	
	@Override
	public void syncCacheInStorage() {
		// TODO Auto-generated method stub
		
	}
}
