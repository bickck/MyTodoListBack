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
import com.todo.list.repository.TodoRepository;
import com.todo.list.repository.mapper.QuoteMapper;
import com.todo.list.repository.quote.QuoteRepository;
import com.todo.list.service.user.QuoteService;
import com.todo.list.util.Utils;
import com.todo.list.repository.UserRepository;
import com.todo.list.repository.image.UserImageRepository;

@Service
public class UserApiService {

	private JPAQueryFactory jpaQueryFactory;
	private UserRepository userRepository;
	private TodoRepository todoRepository;
	private QuoteRepository quoteRepository;
	private UserImageRepository userImageRepository;
	private Utils utils = new Utils();

	@Autowired
	public UserApiService(JPAQueryFactory jpaQueryFactory, UserRepository userRepository, TodoRepository todoRepository,
			QuoteRepository quoteRepository, UserImageRepository userImageRepository) {
		this.jpaQueryFactory = jpaQueryFactory;
		this.userRepository = userRepository;
		this.todoRepository = todoRepository;
		this.quoteRepository = quoteRepository;
		this.userImageRepository = userImageRepository;

	}

	/**
	 * 
	 * @param username
	 * @param user     id
	 * @return User Intro Data
	 */

	@Transactional(readOnly = true)
	public UserIntroDTO getUserIntroDetailsApi(Long id, String username) {
		UserEntity userEntity = userRepository.findByUsername(username);
		UserIntroDTO userIntroDTO = new UserIntroDTO();

		if (userEntity.equals(null)) {
			return null;
		}

		userIntroDTO.setId(userEntity.getId());
		userIntroDTO.setUsername(userEntity.getUsername());
		String introComment = userEntity.getIntroComment();

		if (introComment == null) {
			userIntroDTO.setIntroComment(utils.nvl(introComment));
		}

		return userIntroDTO;
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
	public Page<QuoteEntity> getUserquotes(UserTokenDTO userDTO, Pageable pageable) {
		long id = userDTO.getId();
		Page<QuoteEntity> entities = quoteRepository.findQuoteEntitiesByUserId(id, pageable);
		return entities;
	}

	/**
	 * 
	 * @param userDTO
	 * @param pageable
	 * @return
	 */

	@Transactional(readOnly = true)
	public Page<UserImageEntity> getUserBackGrounds(UserTokenDTO userDTO, Pageable pageable) {

		return null;
	}

	/**
	 * 
	 * @param userDTO
	 * @param pageable
	 * @return User Todo List
	 */
	@Transactional(readOnly = true)
	public Page<TodoEntity> getUserToDoLists(UserTokenDTO userDTO, Pageable pageable) {
		Long id = userDTO.getId();
		Page<TodoEntity> entities = todoRepository.findTodoEntitiesByUserId(id, pageable);

		return entities;
	}

}
