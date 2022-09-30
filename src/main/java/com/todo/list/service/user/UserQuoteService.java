package com.todo.list.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todo.list.controller.builder.QuoteBuilder;
import com.todo.list.controller.dto.auth.UserTokenDTO;
import com.todo.list.controller.dto.service.QuoteDTO;
import com.todo.list.entity.UserEntity;
import com.todo.list.entity.QuoteEntity;
import com.todo.list.repository.UserQuoteRepository;
import com.todo.list.repository.UserRepository;

@Service
public class UserQuoteService {

	@Autowired
	private UserRepository repository;

	@Autowired
	private UserQuoteRepository userQuoteRepository;

	public QuoteEntity quoteSave(QuoteDTO quoteDTO, UserTokenDTO userTokenDTO) {
		UserEntity userEntity = repository.findByUsername(userTokenDTO.getUsername());

		return userQuoteRepository.save(new QuoteEntity(userEntity, quoteDTO));
	}

	public QuoteEntity quoteUpdate(Long id, QuoteDTO quoteDTO, UserTokenDTO tokenDTO) {
		UserEntity userEntity = repository.findByUsername(tokenDTO.getUsername());

		return userQuoteRepository.save(new QuoteEntity(userEntity, quoteDTO));
	}

	public void quoteDelete(Long id) {
		userQuoteRepository.deleteById(id);
	}

}
