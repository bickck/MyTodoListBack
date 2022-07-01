package com.todo.list.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class CacheService{

	@Autowired
	private RedisTemplate<Object, Object> redisTemplate;

	
	@Autowired
	private RedisCacheManager redisCacheManager;
//	
//	
//	public void cache() {
//		System.out.println(redisCacheManager.toString());
//		
//	}
}
