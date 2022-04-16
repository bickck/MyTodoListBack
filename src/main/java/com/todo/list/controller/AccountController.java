package com.todo.list.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.todo.list.controller.dto.UserDTO;
import com.todo.list.domain.UserEntity;
import com.todo.list.service.user.UserService;

@Controller
@RequestMapping(value = "/account")
public class AccountController {

	private static final String SEESION_NAME = "username";
	private static final String CLIENT_SERVER_ADDRESS = "http://127.0.0.1:5501/";

	private UserService userService;

	@Autowired
	public AccountController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping("/login")
	public String loginRequest(@RequestBody UserDTO userDTO, HttpSession httpSession) {

		if (httpSession.getAttribute(SEESION_NAME).equals(null)) {
			UserEntity userEntity = userService.userLogin(userDTO);
			httpSession.setAttribute(SEESION_NAME, userEntity);

		}

		return "redirect:http://127.0.0.1:5501/test2.html";
	}

	@PostMapping("/register")
	public ResponseEntity<String> registerRequest(@RequestBody UserDTO userDTO) {
		userService.userSave(userDTO);
		return new ResponseEntity<String>(HttpStatus.OK);
	}

}
