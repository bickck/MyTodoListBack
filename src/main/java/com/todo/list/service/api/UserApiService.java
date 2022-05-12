package com.todo.list.service.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.todo.list.controller.dto.BackGroundDTO;
import com.todo.list.controller.dto.UserDTO;
import com.todo.list.controller.dto.UserTokenDTO;
import com.todo.list.entity.UserEntity;
import com.todo.list.entity.UserQuoteEntity;
import com.todo.list.entity.UserTodoEntity;
import com.todo.list.repository.UserTodoRepository;
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
	public List<UserQuoteEntity> getUserquotes(UserTokenDTO userDTO, Pageable pageable) {
		String username = userDTO.getUsername();

		return null;// userQuoteRepository.findQuoteEntitiesByUsername(username, pageable);
	}

	@Transactional(readOnly = true)
	public List<BackGroundDTO> getUserBackGrounds(UserTokenDTO userDTO, Pageable pageable) {

		return null;
	}

	@Transactional(readOnly = true)
	public List<UserTodoEntity> getUserToDoLists(UserTokenDTO userDTO, Pageable pageable) {
		String username = userDTO.getUsername();
		return null; // userTodoRepository.findTodoEntitiesByUsername(username, pageable);
	}

}
