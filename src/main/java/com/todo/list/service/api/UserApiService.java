package com.todo.list.service.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.todo.list.controller.dto.auth.UserTokenDTO;
import com.todo.list.controller.dto.user.UserDTO;
import com.todo.list.controller.dto.user.UserIntroDTO;
import com.todo.list.entity.UserImageEntity;
import com.todo.list.entity.UserEntity;
import com.todo.list.entity.QuoteEntity;
import com.todo.list.entity.TodoEntity;
import com.todo.list.repository.QuoteRepository;
import com.todo.list.repository.TodoRepository;
import com.todo.list.repository.mapper.QuoteMapper;
import com.todo.list.repository.mapper.TodoMapper;
import com.todo.list.repository.mapper.UserIntroMapper;
import com.todo.list.service.user.QuoteService;
import com.todo.list.util.Utils;
import com.todo.list.repository.UserRepository;
import com.todo.list.repository.image.UserImageRepository;

@Service
public class UserApiService {

	private UserRepository userRepository;
	private TodoRepository todoRepository;
	private QuoteRepository quoteRepository;

	@Autowired
	public UserApiService(JPAQueryFactory jpaQueryFactory, UserRepository userRepository, TodoRepository todoRepository,
			QuoteRepository quoteRepository) {
		this.userRepository = userRepository;
		this.todoRepository = todoRepository;
		this.quoteRepository = quoteRepository;

	}

	/**
	 * 
	 * @param username
	 * @param user     id
	 * @return User Intro Data
	 */

	@Transactional(readOnly = true)
	public UserIntroMapper getUserIntroDetailsApi(Long id, String email) {
//		UserEntity userEntity = userRepository.findUserIntroInfoByIdAndEmail(id, email);
//
//		UserIntroDTO userIntroDTO = new UserIntroDTO();
//
//		if (userEntity.equals(null)) {
//			return null;
//		}

		return userRepository.findUserIntroInfoByIdAndEmail(id, email);
	}

	/**
	 * 
	 * @param username
	 * @param user     id
	 * @return User Intro Data
	 */

	@Transactional(readOnly = true)
	public UserIntroMapper getUserIntroDetailsApi(String username) {

		return userRepository.findUserIntroInfoByUsername(username);
	}

	/**
	 * 
	 * @param username
	 * @return
	 */

	@Transactional(readOnly = true)
	public UserEntity getUserApi(UserTokenDTO username) {

		return userRepository.findByUsername(username.getUsername());
	}

	/**
	 * 
	 * @param userDTO
	 * @param pageable
	 * @return User Quote List
	 */

	@Transactional(readOnly = true)
	public Page<QuoteMapper> getUserQuotes(UserTokenDTO userDTO, Pageable pageable) {
		long userid = userDTO.getId();
		String email = userDTO.getEmail();
		Page<QuoteMapper> page = quoteRepository.findUserQuoteByUserIdAndUserEmail(userid, email, pageable);

		return page;
	}

	/**
	 * 
	 * @param userDTO
	 * @param pageable
	 * @return
	 */

	@Transactional(readOnly = true)
	public Page<TodoMapper> getUserTodos(UserTokenDTO userDTO, Pageable pageable) {
		long userid = userDTO.getId();
		String email = userDTO.getEmail();
		Page<TodoMapper> entities = todoRepository.findUserTodoByUserIdAndEmail(userid, email, pageable);
		return entities;
	}
	
	
	/**
	 * 
	 * @param id
	 * @return
	 */

	public UserEntity findUserEntityByUserId(Long id) {

		return userRepository.findById(id).get();
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */

	public String findUserPersonalChannelName(Long id) {

		return userRepository.findUserPersonalMessageChannelNameByUserId(id);
	}
}
