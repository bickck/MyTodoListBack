package com.todo.list.redis.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.todo.list.service.user.TodoService;

@Service
public class TodoCacheService implements CacheStorageManager {

//	@Autowired
//	private RedisTemplate<Object, Object> redisTemplate;
//
//	@Autowired
//	private RedisCacheManager redisCacheManager;

	@Autowired
	private TodoService userTodoService;

	/**
	 * 
	 * DB의 부하를 줄이기 위해 DB의 내용을 redis를 옮기며 Read 전용 메모리로 사용
	 * 
	 * DB CUD 발생 시 Redis 내용 업데이트
	 * 
	 * Redis에서 가져온 데이터를 Page<?>로 변경
	 * 
	 * 단일 데이터 조회 시에도 캐시 접근
	 * 
	 * 복수 데이터 접근 때의 알고리즘 설계
	 * 
	 */

	@Override
	public void syncCacheInStorage() {
		// TODO Auto-generated method stub
		
		
	}
	
	
//	public void test() {
//		Map<String, RedisCacheConfiguration> redis = redisCacheManager.getCacheConfigurations();
//
//		redis.forEach((t, u) -> {
//			RedisCacheConfiguration redisConfiguration = u;
//			redisConfiguration.getTtl();
//			System.out.println("Key :" + t + "Value TTL : " + redisConfiguration.getTtl());
//		});
//	}
	
}
