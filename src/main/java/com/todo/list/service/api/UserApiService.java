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
import com.todo.list.entity.UserBackGroundImageEntity;
import com.todo.list.entity.UserEntity;
import com.todo.list.entity.UserQuoteEntity;
import com.todo.list.entity.UserTodoEntity;
import com.todo.list.repository.UserTodoRepository;
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
	private UserTodoRepository userTodoRepository;

	@Autowired
	private UserQuoteRepository userQuoteRepository;

	@Autowired
	private UserImageRepository userImageRepository;

	public Page<UserEntity> getUserList(Pageable pageable) {
		return userRepository.findAll(pageable);
	}

	@Transactional(readOnly = true)
	public UserEntity getUserApi(UserTokenDTO username) {

		return userRepository.findByUsername(username.getUsername());
	}

	@Transactional(readOnly = true)
	public Page<UserQuoteEntity> getUserquotes(UserTokenDTO userDTO, Pageable pageable) {
		long id = userDTO.getId();
		Page<UserQuoteEntity> entities = userQuoteRepository.findQuoteEntitiesByUserId(id, pageable);
		return entities;
	}

	@Transactional(readOnly = true)
	public Page<UserBackGroundImageEntity> getUserBackGrounds(UserTokenDTO userDTO, Pageable pageable) {
		long id = userDTO.getId();
		return null;
	}

	@Transactional(readOnly = true)
	public Page<UserTodoEntity> getUserToDoLists(UserTokenDTO userDTO, Pageable pageable) {
		Long id = userDTO.getId();
		Page<UserTodoEntity> entities = userTodoRepository.findTodoEntitiesByUserId(id, pageable);

		return entities;
	}

}
