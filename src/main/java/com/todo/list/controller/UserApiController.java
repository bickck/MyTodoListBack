package com.todo.list.controller;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

import com.todo.list.configs.token.AuthenticationJwtToken;
import com.todo.list.controller.builder.QuoteBuilder;
import com.todo.list.controller.builder.page.PageQuoteBuilder;
import com.todo.list.controller.builder.page.PageTodoBuilder;
import com.todo.list.controller.dto.auth.UserTokenDTO;
import com.todo.list.controller.dto.page.PageQuoteDTO;
import com.todo.list.controller.dto.page.PageTodoDTO;
import com.todo.list.controller.dto.service.BackGroundDTO;
import com.todo.list.controller.dto.service.QuoteDTO;
import com.todo.list.controller.dto.service.TodoDTO;
import com.todo.list.entity.UserBackGroundImageEntity;
import com.todo.list.entity.UserEntity;
import com.todo.list.entity.UserQuoteEntity;
import com.todo.list.entity.UserTodoEntity;
import com.todo.list.repository.UserQuoteRepository;
import com.todo.list.repository.UserRepository;
import com.todo.list.repository.UserTodoRepository;
import com.todo.list.repository.mapper.QuoteMapper;
import com.todo.list.service.api.UserApiService;
import com.todo.list.service.queto.UserQuoteService;
import com.todo.list.service.user.UserService;
import com.todo.list.util.auth.UserAuthToken;

@RestController
@RequestMapping(value = "/user/api")
public class UserApiController {

	private UserService userService;
	private UserApiService userApiService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserQuoteRepository userQuoteRepository;

	@Autowired
	private AuthenticationJwtToken jwtLoginToken;

	@Autowired
	public UserApiController(UserService userService, UserApiService userApiService) {
		this.userService = userService;
		this.userApiService = userApiService;

	}

//	@PostMapping("/user")
//	public Page<UserEntity> getUser(@PathVariable Integer id, @UserAuthToken UserTokenDTO userTokenDTO) {
//		PageRequest pageRequest = PageRequest.of(id, 10, Sort.Direction.ASC, "id");
//		return userApiService.getUserList(pageRequest);
//	}

	@PostMapping("/quotes")
	public ResponseEntity<PageQuoteDTO> getUserApiQuotes(
			@PageableDefault(size = 8, direction = Direction.ASC) Pageable pageable) {
		UserTokenDTO userTokenDTO = new UserTokenDTO((long) 1, "username0");

		Page<UserQuoteEntity> entities = userApiService.getUserquotes(userTokenDTO, pageable);
		PageQuoteBuilder builder = new PageQuoteBuilder();
		builder.setLists(entities.getContent());
		builder.setNumber(entities.getNumber());
		builder.setNumberOfElements(entities.getNumberOfElements());
		builder.setPageable(entities.getPageable());
		builder.setSize(entities.getSize());
		builder.setTotalPages(entities.getTotalPages());
		builder.setTotalElements(entities.getTotalElements());
		return new ResponseEntity<PageQuoteDTO>(builder.builder(), HttpStatus.OK);
	}

//	@PostMapping("/backgrounds")
//	public ResponseEntity<List<BackGroundDTO>> getUserApiBackGrounds(@UserAuthToken UserTokenDTO userTokenDTO,
//			@PageableDefault(size = 10, direction = Direction.ASC) Pageable pageable) {
//
//		Page<UserBackGroundImageEntity> entities = userApiService.getUserBackGrounds(userTokenDTO, pageable);
//
//		return new ResponseEntity<List<BackGroundDTO>>(null, HttpStatus.OK);
//	}

	@PostMapping("/todos")
	public ResponseEntity<PageTodoDTO> getUserApiTodos(@UserAuthToken UserTokenDTO userTokenDTO,
			@PageableDefault(size = 8, direction = Direction.ASC) Pageable pageable) {

		Page<UserTodoEntity> entities = userApiService.getUserToDoLists(userTokenDTO, pageable);

		PageTodoBuilder builder = new PageTodoBuilder();
		builder.setLists(entities.getContent());
		builder.setNumber(entities.getNumber());
		builder.setNumberOfElements(entities.getNumberOfElements());
		builder.setPageable(entities.getPageable());
		builder.setSize(entities.getSize());
		builder.setTotalPages(entities.getTotalPages());
		builder.setTotalElements(entities.getTotalElements());

		return new ResponseEntity<PageTodoDTO>(builder.builder(), HttpStatus.OK);
	}

	@PostMapping("/quote/{id}")
	public ResponseEntity<List<QuoteDTO>> getUserApiQuotesByid(@PathVariable Integer id,
			@UserAuthToken UserTokenDTO userTokenDTO) {
		PageRequest pageRequest = PageRequest.of(id, 10, Sort.Direction.ASC, "id");

//		Iterator<UserQuoteEntity> itr = userApiService.getUserquotes(userTokenDTO, pageRequest).listIterator();
		List<QuoteDTO> list = new ArrayList<QuoteDTO>();
//		while (itr.hasNext()) {
//			QuoteBuilder builder = new QuoteBuilder();
//			builder.setId(itr.next().getId());
//			builder.setAuthor(itr.next().getAuthor());
//			builder.setQuote(itr.next().getQueto());
//			builder.setUser(itr.next().getUser());
//			list.add(builder.builder());
//		}
		return new ResponseEntity<List<QuoteDTO>>(list, HttpStatus.OK);
	}

	@PostMapping("/background/{id}")
	public ResponseEntity<List<BackGroundDTO>> getUserApiBackGroundsByid(@PathVariable Integer id,
			@UserAuthToken UserTokenDTO userTokenDTO) {
		PageRequest pageRequest = PageRequest.of(id, 10, Sort.Direction.ASC, "id");

		List<BackGroundDTO> list = null;
		userApiService.getUserBackGrounds(userTokenDTO, pageRequest);
		return new ResponseEntity<List<BackGroundDTO>>(list, HttpStatus.OK);
	}

	@PostMapping("/todo/{id}")
	public ResponseEntity<List<TodoDTO>> getUserApiTodosByid(@PathVariable Integer id,
			@UserAuthToken UserTokenDTO userTokenDTO) {
		PageRequest pageRequest = PageRequest.of(id, 10, Sort.Direction.ASC, "id");

		List<TodoDTO> list = null; // userApiService.getUserToDoLists(userTokenDTO, pageRequest);

		return new ResponseEntity<List<TodoDTO>>(list, HttpStatus.OK);
	}

	// @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.ASC)
	// Pageable pageable

}
