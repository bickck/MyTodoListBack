package com.todo.list.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todo.list.service.util.UserUtil;

@RestController
public class ValidController {

	@Autowired
	private UserUtil userUtil;

	@PostMapping("/validation/username")
	public String validUsernameCheck(String username) {

		if (!userUtil.isUsernameDuplicatedCheck(username)) {
			return "unvalid";
		}
		return "valid";
	}

	@PostMapping("/validation/password")
	public String validPasswordCheck(String password) {

		return "valid";
	}
}
