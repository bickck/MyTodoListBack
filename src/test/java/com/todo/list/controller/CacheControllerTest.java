package com.todo.list.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisAccessor;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
class CacheControllerTest {

	@Test
	void test() {
		fail("Not yet implemented");
	}

	@Test
	public void redisRedisCallbackTest() {
		RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<Object, Object>();

		redisTemplate.execute(new RedisCallback<Object>() {
			@Override
			public Object doInRedis(RedisConnection connection) throws DataAccessException {
				// TODO Auto-generated method stub
				return null;
			}
		});

	}

	
	@Test
	public void redisConnection() {
		// RedisConnection connection = new Red
	}

}
