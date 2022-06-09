package com.todo.list.controller;

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

import com.todo.list.controller.builder.QuoteBuilder;
import com.todo.list.controller.builder.TodoBuilder;
import com.todo.list.controller.builder.page.PageTodoBuilder;
import com.todo.list.controller.dto.auth.UserTokenDTO;
import com.todo.list.controller.dto.page.PageTodoDTO;
import com.todo.list.controller.dto.service.QuoteDTO;
import com.todo.list.controller.dto.service.TodoDTO;
import com.todo.list.controller.response.ResponseTodoEntity;
import com.todo.list.entity.TodoEntity;
import com.todo.list.entity.UserEntity;
import com.todo.list.service.api.UserApiService;
import com.todo.list.service.queto.UserQuoteService;
import com.todo.list.service.todo.UserTodoService;
import com.todo.list.util.auth.UserAuthToken;

@RestController
@RequestMapping(value = "/api")
public class TodoController {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	private UserTodoService userTodoService;

	@Autowired
	public TodoController(UserTodoService userTodoService) {
		this.userTodoService = userTodoService;
	}

//	@Cacheable(key = "#pageable.getPageNumber", cacheNames = "todoCache")
//	@GetMapping("/todos")
//	public ResponseEntity<?> requestPublishedTodos(@PageableDefault(size = 50, page = 0) Pageable pageable) {
//		long startTime = System.currentTimeMillis();
//		Page<TodoEntity> page = userTodoService.publishTodos(pageable.getPageNumber(), pageable);
//		long endTime = System.currentTimeMillis();
//
//		PageTodoBuilder builder = new PageTodoBuilder().setLists(page.getContent()).setNumber(page.getNumber())
//				.setPageable(page.getPageable()).setNumberOfElements(page.getNumberOfElements()).setSize(page.getSize())
//				.setTotalPages(page.getTotalPages()).setTotalElements(page.getTotalElements());
//
//		logger.info("Time : {}ms", endTime - startTime);
//
//		return new ResponseEntity<PageTodoDTO>(builder.builder(), HttpStatus.OK);
//	}

	@Cacheable(key = "#pageable.getPageNumber", cacheNames = "todoCache")
	@GetMapping("/todos")
	public Page<TodoDTO> requestPublishedTodos(@PageableDefault(size = 50, page = 0) Pageable pageable) {

		Page<TodoEntity> page = userTodoService.publishTodos(pageable.getPageNumber(), pageable);

		TodoBuilder builder = new TodoBuilder();
		builder.listBuilder(page.getContent());

		return new PageImpl<TodoDTO>(builder.listBuilder(page.getContent()), page.getPageable(),
				page.getTotalElements());
	}

	@CacheEvict()
	@PostMapping("/todo/2/{id}")
	public void requestUserTodoUpdate(@PathVariable Long id, @UserAuthToken UserTokenDTO userTokenDTO,
			@RequestBody TodoDTO todoDTO) {
		//userTodoService.todoUpdate(userTokenDTO, todoDTO, id);

	}

	@PostMapping("/todo/3/{id}")
	public void requestUserTodoDelete() {

	}

	@GetMapping("/recommand/todos")
	public ResponseEntity<?> requestRecommandTodos(@PageableDefault(size = 50, page = 0) Pageable pageable) {

		userTodoService.recommandTodos(pageable);

		return new ResponseEntity<PageTodoDTO>(HttpStatus.OK);
	}

	@PostMapping("/recommand/todo/{id}")
	public ResponseEntity<?> requestRecommandAdd(@PathVariable Long id, @UserAuthToken UserTokenDTO dto) {

		userTodoService.addRecommand(dto, id);

		return new ResponseEntity<String>(HttpStatus.OK);
	}

}
