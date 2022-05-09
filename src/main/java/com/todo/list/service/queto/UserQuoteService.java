package com.todo.list.service.queto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todo.list.controller.builder.QuoteBuilder;
import com.todo.list.controller.builder.UserQuoteEntityBuilder;
import com.todo.list.controller.dto.QuoteDTO;
import com.todo.list.entity.UserEntity;
import com.todo.list.entity.UserQuoteEntity;
import com.todo.list.repository.UserQuoteRepository;
import com.todo.list.repository.UserRepository;

@Service
public class UserQuoteService {

	@Autowired
	private UserRepository repository;

	@Autowired
	private UserQuoteRepository userQuoteRepository;

	public void quoteInsert(QuoteDTO quoteDTO, UserEntity username) {
		
		userQuoteRepository.saveAndFlush(new UserQuoteEntity(username, quoteDTO));
	}

	public void quoteDelete(Long id) {
		userQuoteRepository.deleteById(id);
	}

}
