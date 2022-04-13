package com.todo.list.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.todo.list.controller.dto.UserDTO;
import com.todo.list.service.user.UserService;

@RestController
public class UserController {

	private static final String SEESION_NAME = "username";
	private UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping("/account/login")
	public ResponseEntity loginRequest(@RequestBody UserDTO userDTO, HttpSession httpSession) {

		if(httpSession.getAttribute(SEESION_NAME).equals(null)) {
			userService.userLogin(userDTO);
		}
		
		return new ResponseEntity("hi", HttpStatus.OK);
	}

	@ResponseBody
	@PostMapping("/account/register")
	public ResponseEntity<String> registerRequest(@RequestBody UserDTO userDTO) {

		userService.userSave(userDTO);
		return new ResponseEntity<String>(HttpStatus.OK);
	}
}
