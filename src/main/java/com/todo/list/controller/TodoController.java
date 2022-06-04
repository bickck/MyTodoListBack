package com.todo.list.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
import com.todo.list.controller.dto.auth.UserTokenDTO;
import com.todo.list.controller.dto.page.PageTodoDTO;
import com.todo.list.controller.dto.service.QuoteDTO;
import com.todo.list.entity.TodoEntity;
import com.todo.list.entity.UserEntity;
import com.todo.list.service.api.UserApiService;
import com.todo.list.service.queto.UserQuoteService;
import com.todo.list.service.todo.UserTodoService;
import com.todo.list.util.auth.UserAuthToken;

@RestController
@RequestMapping(value = "/api/todo")
public class TodoController {

	private UserTodoService userTodoService;

	@Autowired
	public TodoController(UserTodoService userTodoService) {
		this.userTodoService = userTodoService;
	}

	@GetMapping("/todos")
	public ResponseEntity<?> requestPublishedTodos(@PageableDefault(size = 8) Pageable pageable) {
		Page<TodoEntity> page = userTodoService.publishTodos(pageable);

		return new ResponseEntity<PageTodoDTO>(HttpStatus.OK);
	}

	@PostMapping("/recommand/{id}")
	public ResponseEntity<?> requestRecommandAdd(@PathVariable Long id, @UserAuthToken UserTokenDTO dto) {

		userTodoService.addRecommand(dto, id);

		return new ResponseEntity<String>(HttpStatus.OK);
	}

	@PostMapping("/ispublish/{id}")
	public ResponseEntity<?> requestUpdatIsPublished(@PathVariable Long id, @UserAuthToken UserTokenDTO dto) {

		userTodoService.addRecommand(dto, id);

		return new ResponseEntity<String>(HttpStatus.OK);
	}

	@PostMapping("/ispublishTest")
	public ResponseEntity<?> requestUpdatIsPublishedTest() {

		userTodoService.updatePublishedTest();

		return new ResponseEntity<String>("success", HttpStatus.OK);
	}

}
