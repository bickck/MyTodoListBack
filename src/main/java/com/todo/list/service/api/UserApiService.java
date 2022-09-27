package com.todo.list.service.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.todo.list.controller.dto.auth.UserTokenDTO;
import com.todo.list.controller.dto.service.BackGroundDTO;
import com.todo.list.controller.dto.user.UserDTO;
import com.todo.list.entity.BackGroundImageEntity;
import com.todo.list.entity.UserEntity;
import com.todo.list.entity.QuoteEntity;
import com.todo.list.entity.TodoEntity;
import com.todo.list.repository.TodoRepository;
import com.todo.list.repository.mapper.QuoteMapper;
import com.todo.list.repository.UserImageRepository;
import com.todo.list.repository.UserQuoteRepository;
import com.todo.list.repository.UserRepository;
import com.todo.list.service.queto.UserQuoteService;

@Service
public class UserApiService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private TodoRepository userTodoRepository;

	@Autowired
	private UserQuoteRepository userQuoteRepository;

	@Autowired
	private UserImageRepository userImageRepository;

	public Page<UserEntity> getUserList(Pageable pageable) {
		return userRepository.findAll(pageable);
	}

	@Transactional(readOnly = true)
	public UserEntity getUserApi(String username) {

		return userRepository.findByUsername(username);
	}

	@Transactional(readOnly = true)
	public UserEntity getUserApi(UserTokenDTO username) {

		return userRepository.findByUsername(username.getUsername());
	}

	@Transactional(readOnly = true)
	public Page<QuoteEntity> getUserquotes(UserTokenDTO userDTO, Pageable pageable) {
		long id = userDTO.getId();
		Page<QuoteEntity> entities = userQuoteRepository.findQuoteEntitiesByUserId(id, pageable);
		return entities;
	}

	@Transactional(readOnly = true)
	public Page<BackGroundImageEntity> getUserBackGrounds(UserTokenDTO userDTO, Pageable pageable) {
	
		return null;
	}

	@Transactional(readOnly = true)
	public Page<TodoEntity> getUserToDoLists(UserTokenDTO userDTO, Pageable pageable) {
		Long id = userDTO.getId();
		Page<TodoEntity> entities = userTodoRepository.findTodoEntitiesByUserId(id, pageable);

		return entities;
	}
	
	@Transactional(readOnly = true)
	public void usersQuoteList() {
		
	}
	

}
