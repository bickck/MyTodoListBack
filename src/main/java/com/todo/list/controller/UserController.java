package com.todo.list.controller;

import java.util.Enumeration;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RestController;

import com.todo.list.controller.dto.LoginUserDTO;
import com.todo.list.domain.UserEntity;
import com.todo.list.security.JwtLoginToken;
import com.todo.list.service.user.UserService;
import com.todo.list.service.util.UserUtil;

import io.jsonwebtoken.Claims;

@RestController
public class UserController {

	private static final String SEESION_NAME = "username";
	private static final String CLIENT_SERVER_ADDRESS = "http://127.0.0.1:5501/";
	private UserService userService;

	@Autowired
	private JwtLoginToken jwtLoginToken;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping("/get/user")
	public ResponseEntity<LoginUserDTO> getUser(HttpServletRequest httpServletRequest) {

		String authorization = httpServletRequest.getHeader("authorization");

		if (authorization != null) {
			String username = jwtLoginToken.getUsername(authorization);
			UserEntity userinfo = userService.getUser(username);

			return new ResponseEntity<LoginUserDTO>(new LoginUserDTO(userinfo), HttpStatus.OK);
		}

		return new ResponseEntity<LoginUserDTO>(HttpStatus.NON_AUTHORITATIVE_INFORMATION);
	}

	@PostMapping("get/valid")
	public Claims getValid(HttpServletRequest httpServletRequest) {

		Enumeration<String> s = httpServletRequest.getHeaderNames();
		String authorization = httpServletRequest.getHeader("authorization");

		Claims body = jwtLoginToken.getUser(authorization);

		return body;
	}
}
