package com.todo.list.jpa;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.todo.list.repository.DefaultImageRepository;

public class QueryTest {
	
	@Autowired
	private DefaultImageRepository defaultImageRepository;

	@Test
	public void findOriginalAndFilePathQueryTest() {
		//defaultImageRepository.findOriginalFileName();
	}
}
