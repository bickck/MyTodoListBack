package com.todo.list.test.controller;

import java.util.List;

import org.hibernate.boot.model.Caching;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.CacheProperties.EhCache;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.ehcache.EhCacheCache;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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
	private final Logger logger = LoggerFactory.getLogger(getClass());

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

	@GetMapping("/test/log")
	public void testLog() {
		logger.info("안녕하세요~ Trace 함수에요~");
	}

	@GetMapping("/test/tracelog")
	public void testLogTrace() {
		logger.trace("안녕하세요~ Trace 함수에요~");
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
}
