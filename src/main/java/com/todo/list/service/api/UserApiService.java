package com.todo.list.service.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.todo.list.controller.dto.BackGroundDTO;
import com.todo.list.controller.dto.auth.UserTokenDTO;
import com.todo.list.controller.dto.user.UserDTO;
import com.todo.list.controller.dto.user.UserIntroDTO;
import com.todo.list.entity.UserImageEntity;
import com.todo.list.entity.UserEntity;
import com.todo.list.entity.QuoteEntity;
import com.todo.list.entity.TodoEntity;
import com.todo.list.repository.TodoRepository;
import com.todo.list.repository.mapper.QuoteMapper;
import com.todo.list.service.user.QuoteService;
import com.todo.list.util.Utils;
import com.todo.list.repository.UserImageRepository;
import com.todo.list.repository.QuoteRepository;
import com.todo.list.repository.UserRepository;

@Service
public class UserApiService {

	@Autowired
	private JPAQueryFactory jpaQueryFactory;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private TodoRepository userTodoRepository;

	@Autowired
	private QuoteRepository userQuoteRepository;

	@Autowired
	private UserImageRepository userImageRepository;

	private Utils utils = new Utils();

	public Page<UserEntity> getUserList(Pageable pageable) {
		return userRepository.findAll(pageable);
	}

	@Transactional(readOnly = true)
	public UserEntity getUserApi(String username) {

		return userRepository.findByUsername(username);
	}

	@Transactional(readOnly = true)
	public UserIntroDTO getUserIntroDetailsApi(String username) {
		UserEntity userEntity = userRepository.findByUsername(username);
		UserIntroDTO userIntroDTO = new UserIntroDTO();

		userIntroDTO.setId(userEntity.getId());
		userIntroDTO.setUsername(userEntity.getUsername());
		String fileName = userEntity.getUserImageEntity().getFileName();
		String location = userEntity.getUserImageEntity().getLocation();
		String introComment = userEntity.getIntroComment();

		if (fileName == null) {
			userIntroDTO.setFileName(utils.nvl(fileName));
		}
		if (location == null) {
			userIntroDTO.setLocation(utils.nvl(location));
		}
		if (introComment == null) {
			userIntroDTO.setIntroComment(utils.nvl(introComment));
		}

		return userIntroDTO;
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
	public Page<UserImageEntity> getUserBackGrounds(UserTokenDTO userDTO, Pageable pageable) {

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
