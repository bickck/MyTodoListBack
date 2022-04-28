package com.todo.list.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todo.list.security.AuthenticationJwtToken;
import com.todo.list.service.api.UserApiService;
import com.todo.list.service.queto.UserQuoteService;
import com.todo.list.service.user.UserService;

@RestController
@RequestMapping(value = "/user/api")
public class UserApiController {

	private UserService userService;
	private UserApiService userApiService;
	private UserQuoteService userQuoteService;

	@Autowired
	private AuthenticationJwtToken jwtLoginToken;

	@Autowired
	public UserApiController(UserService userService, UserApiService userApiService,
			UserQuoteService userQuoteService) {
		this.userService = userService;
		this.userApiService = userApiService;
		this.userQuoteService = userQuoteService;
	}

	//
//	@PostMapping("/get/")
//	public String getQuote() {
//		return "Test Success";
//	}

	@PostMapping("/get/quote/{id}")
	public String getQuote(@PathVariable Long id) {
		return "Test Success";
	}

	@PostMapping("/get/background")
	public String getBackGround() {
		return "Test Success";
	}

	@PostMapping("/get/background/{id}")
	public String getBackGround(@PathVariable Long id) {
		return "Test Success";
	}

	@PostMapping("/get/todo")
	public String getTodo(@PageableDefault(size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
		return "Test Success";
	}

//	@PostMapping("/get/todo/{id}")
//	public String getTodo(@PathVariable Long id) {
//		return "Test Success";
//	}

}
