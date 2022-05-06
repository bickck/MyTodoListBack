package com.todo.list.service.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

	@Transactional(readOnly = true)
	public UserEntity getUserApi(UserTokenDTO username) {

		return userRepository.findByUsername(username.getUsername());
	}

	@Transactional(readOnly = true)
	public List<UserQuoteEntity> getUserquotes(UserTokenDTO userDTO) {
		String username = userDTO.getUsername();

		return null;
	}

	@Transactional(readOnly = true)
	public void getUserBackGround(UserTokenDTO userDTO) {

	}

	@Transactional(readOnly = true)
	public void getUserToDoList(UserTokenDTO userDTO) {

	}
	
	public void userQuotes(UserTokenDTO userDTO) {
		
	}
}
