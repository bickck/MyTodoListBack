package com.todo.list.service.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todo.list.controller.dto.UserDTO;
import com.todo.list.controller.dto.UserTokenDTO;
import com.todo.list.domain.UserEntity;
import com.todo.list.domain.UserQuoteEntity;
import com.todo.list.repository.UserImageRepository;
import com.todo.list.repository.UserQuoteRepository;
import com.todo.list.repository.UserRepository;
import com.todo.list.service.queto.UserQuoteService;

@Service
public class UserApiService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserQuoteRepository userQuoteRepository;

	@Autowired
	private UserImageRepository userImageRepository;

	public UserEntity getUserApi(UserTokenDTO username) {

		return userRepository.findByUsername(username.getUsername());
	}

	public UserQuoteEntity getUserquote(UserDTO userDTO) {
		return new UserQuoteEntity();
	}

	public void getUserBackGround(UserDTO userDTO) {

	}

	public void getUserToDoList(UserDTO userDTO) {

	}
}
