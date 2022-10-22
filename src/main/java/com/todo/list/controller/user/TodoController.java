package com.todo.list.controller.user;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todo.list.controller.ResponseStatus;
import com.todo.list.controller.builder.QuoteBuilder;
import com.todo.list.controller.builder.TodoBuilder;
import com.todo.list.controller.builder.page.PageTodoBuilder;
import com.todo.list.controller.dto.CommentDTO;
import com.todo.list.controller.dto.QuoteDTO;
import com.todo.list.controller.dto.TodoDTO;
import com.todo.list.controller.dto.auth.UserTokenDTO;
import com.todo.list.controller.dto.page.PageTodoDTO;
import com.todo.list.entity.TodoCommentEntity;
import com.todo.list.entity.TodoEntity;
import com.todo.list.entity.UserEntity;
import com.todo.list.service.api.UserApiService;
import com.todo.list.service.user.QuoteService;
import com.todo.list.service.user.TodoService;
import com.todo.list.util.auth.UserAuthToken;

/**
 * 
 * 해당 유저의 Todo 데이터를 저장,수정,삭제를 제공하는 클래스
 * 
 */

@RestController
@RequestMapping(value = "/user/todo/manage")
public class TodoController {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	private TodoService userTodoService;

	@Autowired
	public TodoController(TodoService userTodoService) {
		this.userTodoService = userTodoService;
	}

	/*
	 * Todo save
	 */

	@PostMapping("/save")
	public ResponseEntity<?> saveUserTodo(@RequestBody TodoDTO todoDTO, @UserAuthToken UserTokenDTO userTokenDTO) {

		userTodoService.todoSave(userTokenDTO, todoDTO);

		return new ResponseEntity<String>(ResponseStatus.SUCCESS, HttpStatus.OK);
	}

	@PostMapping("/update/{id}")
	public ResponseEntity<?> updateUserTodo(@PathVariable Long id, @RequestBody TodoDTO todoDTO,
			@UserAuthToken UserTokenDTO userTokenDTO) {

		userTodoService.todoUpdate(todoDTO);

		return new ResponseEntity<String>(ResponseStatus.SUCCESS, HttpStatus.OK);
	}

	@PostMapping("/delete/{id}")
	public ResponseEntity<?> deleteUserTodo(@PathVariable Long id, @UserAuthToken UserTokenDTO userTokenDTO) {

		userTodoService.todoDelete(id);
		return new ResponseEntity<String>(ResponseStatus.SUCCESS, HttpStatus.OK);
	}

	@PostMapping("/heart/add/{id}")
	public ResponseEntity<?> todoCommentHeartAdd(@PathVariable Long id, @UserAuthToken UserTokenDTO userTokenDTO) {

		userTodoService.addHeartUserTodo(id);

		return new ResponseEntity<String>(ResponseStatus.SUCCESS, HttpStatus.OK);
	}

	@PostMapping("/comment/add/{id}")
	public ResponseEntity<?> requestRecommandAdd(@PathVariable Long id, @RequestBody CommentDTO commentDTO,
			@UserAuthToken UserTokenDTO dto) {

		TodoCommentEntity commentEntity = userTodoService.addCommentUserTodo(id, dto, commentDTO);

		if (commentEntity == null) {

		}

		return new ResponseEntity<String>(ResponseStatus.SUCCESS, HttpStatus.OK);
	}
}
