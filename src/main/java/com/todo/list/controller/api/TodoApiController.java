package com.todo.list.controller.api;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todo.list.controller.builder.TodoBuilder;
import com.todo.list.controller.builder.page.PageTodoBuilder;
import com.todo.list.controller.dto.auth.UserTokenDTO;
import com.todo.list.controller.dto.page.PageTodoDTO;
import com.todo.list.controller.dto.service.TodoDTO;
import com.todo.list.entity.UserTodoEntity;
import com.todo.list.service.api.TodoApiService;
import com.todo.list.service.user.UserTodoService;
import com.todo.list.util.auth.UserAuthToken;

@RestController
@RequestMapping("/todo/api")
public class TodoApiController {

	private UserTodoService userTodoService;
	private TodoApiService todoApiService;

	@Autowired
	public TodoApiController(UserTodoService userTodoService, TodoApiService todoApiService) {
		this.userTodoService = userTodoService;
		this.todoApiService = todoApiService;
	}

	// @Cacheable(key = "#pageable.getPageNumber", cacheNames = "todoCache")
	@GetMapping("/mainpost")
	public Page<TodoDTO> requestPublishedTodos(@PageableDefault(size = 10, page = 0) Pageable pageable) {

		Page<UserTodoEntity> page = todoApiService.publishTodos(pageable.getPageNumber(), pageable);

		TodoBuilder builder = new TodoBuilder();
		builder.listBuilder(page.getContent());

		return new PageImpl<TodoDTO>(builder.listBuilder(page.getContent()), page.getPageable(),
				page.getTotalElements());
	}

	@PostMapping("/{id}")
	public ResponseEntity<List<TodoDTO>> requestUserApiTodosByid(@PathVariable Integer id) {
		PageRequest pageRequest = PageRequest.of(id, 10, Sort.Direction.ASC, "id");

		List<TodoDTO> list = null; // userApiService.getUserToDoLists(userTokenDTO, pageRequest);

		return new ResponseEntity<List<TodoDTO>>(list, HttpStatus.OK);
	}

	@GetMapping("/recommand/todos")
	public ResponseEntity<?> requestRecommandTodos(@PageableDefault(size = 50, page = 0) Pageable pageable) {

		todoApiService.recommandTodos(pageable);

		return new ResponseEntity<PageTodoDTO>(HttpStatus.OK);
	}

}
