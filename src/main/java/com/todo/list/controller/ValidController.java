package com.todo.list.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.todo.list.util.UserUtil;

@RestController
public class ValidController {

	@Autowired
	private UserUtil userUtil;

	@PostMapping("/valid/username")
	public ResponseEntity<String> validUsernameCheck(String username) {

//		if (!userUtil.isUsernameDuplicatedCheck(username)) {
//			return new ResponseEntity<String>("unvalid",HttpStatus.NOT_FOUND);
//		}
		return new ResponseEntity<String>("valid",HttpStatus.OK);
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
