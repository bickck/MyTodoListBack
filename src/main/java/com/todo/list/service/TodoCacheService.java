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
	
	/**
	 * 
	 * db의 부하를 줄이기 위해 db의 내용을 redis를 옮기며 Read 전용 메모리로 사용
	 * 
	 * db cud 발생 시 Redis 내용 업데이트
	 * 
	 * Redis에서 가져온 데이터를 Page<?>로 변경
	 * 
	 * */
	
	@Override
	public void syncCacheInStorage() {
		// TODO Auto-generated method stub
		
	}
}
