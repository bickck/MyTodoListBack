package com.todo.list.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.CacheProperties.Redis;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todo.list.RedisCacheManagerName;
import com.todo.list.RedisCacheNames;
import com.todo.list.entity.TodoEntity;
import com.todo.list.service.TodoCacheService;
import com.todo.list.service.todo.UserTodoService;

@RestController
public class TodoTestController implements RedisCacheNames, RedisCacheManagerName {

	@Autowired
	private UserTodoService todoService;

	@Autowired
	private TodoCacheService todoCacheService;

	@PostMapping("/test/todo/isPublishTest/{id}")
	public ResponseEntity<?> requestUpdatIsPublishedTest(@PathVariable Long id) {

		todoService.updatePublishedTest(id);

		return new ResponseEntity<String>(HttpStatus.OK);
	}

	@GetMapping("/test/todo/isPublishTest")
	public ResponseEntity<?> findAllPublishedTest() {

		int count = todoService.findAllPublishTodos().size();

		return new ResponseEntity<Integer>(count, HttpStatus.OK);
	}

	@Cacheable(cacheNames = RedisCacheNames.TodoCacheName, cacheManager = RedisCacheManagerName.TestCacheManagerName, keyGenerator = "testCacheKeyGenerator")
	@GetMapping("/test/todo/cache/{id}")
	public String testCacheStorage(@PathVariable Long id) {

		Long todoId = todoService.findOne(id).getId();

		return "todoId : " + todoId;
	}
	
	@CachePut(cacheNames = RedisCacheNames.TodoCacheName, cacheManager = RedisCacheManagerName.TestCacheManagerName, keyGenerator = "testCacheKeyGenerator")
	@GetMapping("/test/todo/cache/put/{id}")
	public String testCacheAnnotationPut(@PathVariable Long id) {

		Long todoId = todoService.findOne(id).getId();

		return "todoId : " + todoId;
	}
	
	@GetMapping("/test/todo/notuseAnnotation/{id}")
	public String testCacheAnnotationNotUse(@PathVariable Long id) {

		Long todoId = todoService.findOneTestNotCacheAnnotation(id).getId();

		return "todoId : " + todoId;
	}
}
