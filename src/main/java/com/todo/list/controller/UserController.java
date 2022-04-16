package com.todo.list.controller;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RestController;


import com.todo.list.service.user.UserService;
import com.todo.list.service.util.UserUtil;

@RestController
public class UserController {

	private static final String SEESION_NAME = "username";
	private static final String CLIENT_SERVER_ADDRESS = "http://127.0.0.1:5501/";
	private UserService userService;

	@Autowired
	private UserUtil userUtil;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping("/validation/username")
	public String validUsernameCheck(String username) {

		if (userUtil.isUsernameDuplicatedCheck(username)) {
			return "unvalid";
		}

		return "valid";
	}

	@PostMapping("/validation/password")
	public String validPasswordCheck(String password) {

		

		return "valid";
	}
}
