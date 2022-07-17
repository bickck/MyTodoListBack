package com.todo.list.test.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisAccessor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.todo.list.redis.RedisCacheManagerName;
import com.todo.list.redis.RedisCacheNames;
import com.todo.list.redis.service.TodoCacheService;
import com.todo.list.test.Entity.TestEntity;
import com.todo.list.test.repository.TestRepository;
import com.todo.list.test.service.TestService;

//import com.todo.list.configs.cache.CacheConfig;

@RestController
public class TestCacheController {

	@Autowired
	private TestRepository repository;

	@Autowired
	private TestService service;

	@Autowired
	private TodoCacheService cacheService;
	
	@Autowired
	private RedisTemplate<Object, Object> redisTemplate;

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@GetMapping("/test/cache/{id}")
	@Cacheable(cacheNames = RedisCacheNames.TestCacheName, key = "#id", cacheManager = RedisCacheManagerName.TestCacheManagerName)
	public TestEntity testCacheEventLogMethod(@PathVariable Long id) {


		return repository.findById(id).get();

	}

	@GetMapping("test/cache/callManager")
	public Object cacheManager() {
		RedisAccessor redisAccessor = new RedisAccessor();

		RedisConnectionFactory redisConnectionFactory = redisAccessor.getConnectionFactory();

		System.out.println(redisAccessor);
		return "hi";
	}

	@GetMapping("/test/cache/save")
	public void testSaveTestCase() {

		repository.save(new TestEntity("testA", "testB", "testC", "testD", (long) 1, (long) 5));
	}

	@GetMapping("/test/cache")
	public ResponseEntity<?> testCache(@PageableDefault Pageable pageable) {
		// List<TestEntity> page = service.testPageEntity(pageable);

		return new ResponseEntity<>("", HttpStatus.OK);
	}

	@GetMapping("/test/cache/pageable")
	public ResponseEntity<?> testCachePageable(@PageableDefault Pageable pageable) {
		Page<TestEntity> page = service.testCachePageEntity(pageable);

		return new ResponseEntity<>(page, HttpStatus.OK);
	}

	@GetMapping("/test/Notcache/pageable")
	public Pageable testNotCachePageable(@PageableDefault() Pageable pageable) {
		Pageable page = service.testPageEntity(pageable);

		return page;
	}

//	@GetMapping("/test/cache/Custompageable")
//	public PageableTest<TestEntity> testNotCacheCustomPageable(@PageableDefault() Pageable pageable) {
//		PageableTest<TestEntity> page = service.testPageCustomEntity(pageable);
//
//		return page;
//	}

//	@GetMapping("/test/cache/{id}")
//	@Cacheable(cacheNames = "cacheStorage", key = "#id")
//	public TestEntity testCacheEventLogMethod(@PathVariable Long id) {
//
//		return repository.findById(id).get();
//	}

//	@GetMapping("/test/cachememory")
//	public void testCacheMemory() {
//		net.sf.ehcache.CacheManager cacheCacheManager = net.sf.ehcache.CacheManager.create();
//
//		System.out.println(cacheCacheManager.getOriginalConfigurationText());
//		System.out.println(cacheCacheManager.getCache("cacheStorage"));
//		System.out.println(cacheCacheManager.getActiveConfigurationText());
//		System.out.println(cacheCacheManager.getCacheNames());
//		System.out.println(cacheCacheManager.getCacheManagerEventListener());
//		// System.out.println(cacheCacheManager.getOriginalConfigurationText());
//	}
}
