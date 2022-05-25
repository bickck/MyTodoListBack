package com.todo.list.jpa;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.todo.list.repository.AdminImageRepository;

public class QueryTest {
	
	@Autowired
	private AdminImageRepository defaultImageRepository;

	@Test
	public void findOriginalAndFilePathQueryTest() {
		//defaultImageRepository.findOriginalFileName();
	}
}
