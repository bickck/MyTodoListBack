package com.todo.list.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todo.list.controller.builder.TodoBuilder;
import com.todo.list.controller.builder.page.PageTodoBuilder;
import com.todo.list.controller.dto.auth.UserTokenDTO;
import com.todo.list.controller.dto.page.PageTodoDTO;
import com.todo.list.controller.dto.service.TodoDTO;
import com.todo.list.entity.TodoEntity;
import com.todo.list.service.todo.UserTodoService;
import com.todo.list.util.auth.UserAuthToken;

@RestController
public class TodoApiController {

	private UserTodoService userTodoService;

	@Autowired
	public TodoApiController(UserTodoService userTodoService) {
		this.userTodoService = userTodoService;
	}

	// @Cacheable(key = "#pageable.getPageNumber", cacheNames = "todoCache")
	@GetMapping("/todoData")
	public Page<TodoDTO> requestPublishedTodos(@PageableDefault(size = 50, page = 0) Pageable pageable) {

		Page<TodoEntity> page = userTodoService.publishTodos(pageable.getPageNumber(), pageable);

		TodoBuilder builder = new TodoBuilder();
		builder.listBuilder(page.getContent());

		return new PageImpl<TodoDTO>(builder.listBuilder(page.getContent()), page.getPageable(),
				page.getTotalElements());
	}

	@PostMapping("/todo/{id}")
	public ResponseEntity<List<TodoDTO>> getUserApiTodosByid(@PathVariable Integer id,
			@UserAuthToken UserTokenDTO userTokenDTO) {
		PageRequest pageRequest = PageRequest.of(id, 10, Sort.Direction.ASC, "id");

		List<TodoDTO> list = null; // userApiService.getUserToDoLists(userTokenDTO, pageRequest);

		return new ResponseEntity<List<TodoDTO>>(list, HttpStatus.OK);
	}
	
	

	@PostMapping("/todo/isPublish/{id}")
	public ResponseEntity<?> requestUpdatIsPublished(@PathVariable Long id, @UserAuthToken UserTokenDTO dto) {

		userTodoService.updatePublished(id, dto.getUsername());

		return new ResponseEntity<String>(HttpStatus.OK);
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
