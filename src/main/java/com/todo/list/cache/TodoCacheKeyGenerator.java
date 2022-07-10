package com.todo.list.cache;

import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.interceptor.KeyGenerator;

public class TodoCacheKeyGenerator implements KeyGenerator {

	private static final String KEY_FORMAT = "todo:";

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public Object generate(Object target, Method method, Object... params) {
		// TODO Auto-generated method stub
		logger.info("TodoCacheKeyGenerator : TargetMethd => {}", target);
		String id = String.valueOf(params[0]);
		return KEY_FORMAT + id;
	}
}
