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
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todo.list.controller.builder.QuoteBuilder;
import com.todo.list.controller.dto.BackGroundDTO;
import com.todo.list.controller.dto.QuoteDTO;
import com.todo.list.controller.dto.TodoDTO;
import com.todo.list.controller.dto.UserTokenDTO;
import com.todo.list.entity.UserEntity;
import com.todo.list.entity.UserQuoteEntity;
import com.todo.list.entity.UserTodoEntity;
import com.todo.list.repository.UserQuoteRepository;
import com.todo.list.repository.UserRepository;
import com.todo.list.repository.UserTodoRepository;
import com.todo.list.security.AuthenticationJwtToken;
import com.todo.list.service.api.UserApiService;
import com.todo.list.service.queto.UserQuoteService;
import com.todo.list.service.user.UserService;
import com.todo.list.util.aop.UserAuthToken;

@RestController
//@RequestMapping(value = "/user/api")
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

	@GetMapping("/users/{id}")
	public Page<UserEntity> getUsers(@PathVariable Integer id) {
		PageRequest pageRequest = PageRequest.of(id, 10, Sort.Direction.ASC, "id");
		return userApiService.getUserList(pageRequest);
	}

	@GetMapping("/quotes/{id}")
	public ResponseEntity<List<QuoteDTO>> getQuotes(@PathVariable Integer id,
			@UserAuthToken UserTokenDTO userTokenDTO) {
		PageRequest pageRequest = PageRequest.of(id, 10, Sort.Direction.ASC, "id");

		Iterator<UserQuoteEntity> itr = userApiService.getUserquotes(userTokenDTO, pageRequest).listIterator();
		List<QuoteDTO> list = new ArrayList<QuoteDTO>();
		while (itr.hasNext()) {
			QuoteBuilder builder = new QuoteBuilder();
			builder.setId(itr.next().getId());
			builder.setAuthor(itr.next().getAuthor());
			builder.setQuote(itr.next().getQueto());
			builder.setUser(itr.next().getUser());
			list.add(builder.builder());
		}
		return new ResponseEntity<List<QuoteDTO>>(list, HttpStatus.OK);
	}

	@GetMapping("/backgrounds/{id}")
	public ResponseEntity<List<BackGroundDTO>> getBackGrounds(@PathVariable Integer id,
			@UserAuthToken UserTokenDTO userTokenDTO) {
		PageRequest pageRequest = PageRequest.of(id, 10, Sort.Direction.ASC, "id");

		List<BackGroundDTO> list = userApiService.getUserBackGrounds(userTokenDTO, pageRequest);
		return new ResponseEntity<List<BackGroundDTO>>(list, HttpStatus.OK);
	}

	@GetMapping("/todos/{id}")
	public ResponseEntity<List<TodoDTO>> getTodos(@PathVariable Integer id, @UserAuthToken UserTokenDTO userTokenDTO) {
		PageRequest pageRequest = PageRequest.of(id, 10, Sort.Direction.ASC, "id");

		List<TodoDTO> list = null; // userApiService.getUserToDoLists(userTokenDTO, pageRequest);

		return new ResponseEntity<List<TodoDTO>>(list, HttpStatus.OK);
	}

	@GetMapping("/todo/{id}")
	public Optional<UserQuoteEntity> getTodo(@PathVariable Long id) {
		// UserEntity entity = userRepository.getById(id);

		UserQuoteEntity entity= userQuoteRepository.findById(id).get();
		System.out.println(entity.toString());
		System.out.println(entity.getUser().toString());
		return null;
	}

	// @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.ASC)
	// Pageable pageable

}
