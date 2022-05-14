package com.todo.list.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

	@Autowired
	private TestService service;

	@GetMapping("/test/ArgCache")
	public String testArgCache() {
		long id = 1;
		String testEntity = service.testArgCacheService(id);
		return testEntity;
	}

	@GetMapping("/test/noArgCache")
	public String testNoArgCache() {

		String testEntity = service.testNoArgCacheService();
		return testEntity;
	}
}
