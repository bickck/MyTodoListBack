package com.todo.list.test;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todo.list.domain.UserEntity;
import com.todo.list.domain.UserQuoteEntity;
import com.todo.list.repository.UserRepository;

@Service
public class TestService {

	@Autowired
	private UserRepository userRepository;
	
//	
//	public void saveQuotes() {
//		UserEntity entity = userRepository.findByUsername("1234");
//		List<UserQuoteEntity> entities = new ArrayList();
//		entities.add(new UserQuoteEntity(entity.getUsername(), "quote1", "quote1"));
//		entities.add(new UserQuoteEntity(entity.getUsername(), "quote2", "quote2"));
//		entities.add(new UserQuoteEntity(entity.getUsername(), "quote3", "quote3"));
//		entities.add(new UserQuoteEntity(entity.getUsername(), "quote4", "quote4"));
//		entity.setQuotes(entities);
//	}
}
