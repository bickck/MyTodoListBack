package com.todo.list.test;

import java.lang.reflect.Method;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.interceptor.CacheOperation;
import org.springframework.cache.interceptor.CacheOperationSource;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.stereotype.Service;

@Configuration
public class CustomCacheAnnotation implements CacheOperationSource {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public Collection<CacheOperation> getCacheOperations(Method method, Class<?> targetClass) {
		// TODO Auto-generated method stub
		logger.info("Hi execute Method");
		return null;
	}

}
