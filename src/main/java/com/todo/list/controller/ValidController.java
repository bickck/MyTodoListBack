package com.todo.list.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.todo.list.util.UserUtil;

@RestController
public class ValidController {

	@Autowired
	private UserUtil userUtil;

	@PostMapping("/valid/username")
	public String validUsernameCheck(String username) {

		if (!userUtil.isUsernameDuplicatedCheck(username)) {
			return "unvalid";
		}
		return "valid";
	}

	@PostMapping("/valid/password")
	public String validPasswordCheck(String password) {

		return "valid";
	}

	@PostMapping("/valid/token")
	public String refreshToken(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

		String authorization = httpServletRequest.getHeader("authorization");

		return "1234";
	}
}
