package com.todo.list.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

	@Autowired
	private TestService service;

	@GetMapping("/test/cache")
	public String testCache() {
		long id = 1;
		String testEntity = service.testCacheService(id);
		return testEntity;
	}
}
