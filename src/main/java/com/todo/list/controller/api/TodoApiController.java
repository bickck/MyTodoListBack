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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.todo.list.controller.builder.TodoBuilder;
import com.todo.list.controller.builder.page.PageTodoBuilder;
import com.todo.list.controller.dto.TodoDTO;
import com.todo.list.controller.dto.auth.UserTokenDTO;
import com.todo.list.controller.dto.page.PageTodoDTO;
import com.todo.list.entity.TodoEntity;
import com.todo.list.repository.mapper.TodoCommentMapper;
import com.todo.list.repository.mapper.TodoMapper;
import com.todo.list.service.api.TodoApiService;
import com.todo.list.service.api.TodoCommentApiService;
import com.todo.list.service.user.TodoService;
import com.todo.list.util.auth.UserAuthToken;

/**
 * 로그인 필요 없이 Todo API를 제공하는 클래스
 * 
 */

@RestController
@RequestMapping("/todo/api")
public class TodoApiController {

	private TodoApiService todoApiService;
	private TodoCommentApiService todoCommentApiService;

	@Autowired
	public TodoApiController(TodoCommentApiService todoCommentApiService, TodoApiService todoApiService) {
		this.todoCommentApiService = todoCommentApiService;
		this.todoApiService = todoApiService;
	}

	/**
	 * Todo의 자세한 정보를 가져옴
	 * 
	 * @param Todo Unique Id
	 * @return Todo Detail
	 */

	@GetMapping("/{id}")
	@ResponseBody
	public ResponseEntity<?> requestPostDetailByid(@PathVariable Long id) {

		TodoEntity todoDetail = todoApiService.findPostDetailById(id);

		return new ResponseEntity<TodoEntity>(todoDetail, HttpStatus.OK);
	}

	/**
	 * Publish된 모든 Todo를 가져옴
	 * 
	 * @param pageable
	 * @return All Todo list
	 */

	@GetMapping("/mainpost")
	@ResponseBody
	public ResponseEntity<?> requestPublishedTodos(@PageableDefault(size = 5, page = 0) Pageable pageable) {

		Page<TodoMapper> page = todoApiService.findMainTodos(pageable);

		return new ResponseEntity<Page<TodoMapper>>(page, HttpStatus.OK);
	}

	/**
	 * HEART가 가장 많은 Todo List를 가져옴
	 * 
	 * @param pageable
	 * @return Recommand All Todo
	 */

	@GetMapping("/recommand")
	@ResponseBody
	public ResponseEntity<?> requestRecommandTodos(@PageableDefault(size = 5, page = 0) Pageable pageable) {

		Page<TodoMapper> page = todoApiService.findMostRecommandDailyTodos(pageable);

		return new ResponseEntity<Page<TodoMapper>>(page, HttpStatus.OK);

	}

	/**
	 * 해당 날짜에 HEART를 가장 많이 받은 Todo List를 가져옴
	 * 
	 * @param pageable
	 * @return Recommand Daily All Todo List
	 */

	@GetMapping("/daily")
	@ResponseBody
	public ResponseEntity<?> requestDailyTodos(@PageableDefault(size = 5, page = 0) Pageable pageable) {

		Page<TodoMapper> page = todoApiService.findRecommandTodos(pageable);

		return new ResponseEntity<Page<TodoMapper>>(page, HttpStatus.OK);
	}

	/**
	 * 
	 * Todo의 Comment를 가져옴
	 * 
	 * @param todo id
	 * @param pageable
	 * @return Comment List of Todo Id
	 */

	@GetMapping("/comment/{id}")
	@ResponseBody
	public ResponseEntity<?> requestCommentsByTodoId(@PathVariable Long id,
			@PageableDefault(size = 10, page = 0) Pageable pageable) {

		Page<TodoCommentMapper> page = todoCommentApiService.findTodoCommentsByTodoId(id, pageable);

		return new ResponseEntity<Page<TodoCommentMapper>>(page,HttpStatus.OK);
	}

}
