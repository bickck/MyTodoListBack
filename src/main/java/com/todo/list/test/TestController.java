package com.todo.list.test;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todo.list.configs.token.AuthenticationJwtToken;
import com.todo.list.entity.UserEntity;
import com.todo.list.entity.UserQuoteEntity;
import com.todo.list.repository.UserQuoteRepository;
import com.todo.list.repository.UserRepository;

@RestController
public class TestController {

	@Autowired
	private TestService service;

	@Autowired
	private UserRepository repository;

	@Autowired
	private UserQuoteRepository quoteRepository;

	@Autowired
	private AuthenticationJwtToken authenticationJwtToken;

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

	@GetMapping("/test/jwtProblem")
	public String testJwtProblem() {

		String token = authenticationJwtToken.makeToken(repository.findByUsername("username0"));

		return token;
	}

	@GetMapping("/test/quote/querytest1")
	public String testQuoteQueryTest() {
		UserEntity entity = repository.findById((long) 1).get();

		long startTime = System.currentTimeMillis();
//		Iterator<UserQuoteEntity> lists = quoteRepository.findQuoteEntitiesByUser(entity).iterator();
		long endTime = System.currentTimeMillis();
		
		return endTime - startTime + "ms";
	}

	@GetMapping("/test/quote/querytest2")
	public String testQuoteQueryTest2() {
		UserEntity entity = repository.findById((long) 1).get();
		
		long startTime = System.currentTimeMillis();
//		Iterator<UserQuoteEntity> lists = quoteRepository.findQuoteEntitiesByUserId(entity.getId()).iterator();
		long endTime = System.currentTimeMillis();

		return endTime - startTime + "ms";
	}
}
