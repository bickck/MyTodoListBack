package com.todo.list.controller;

import java.util.Enumeration;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.todo.list.controller.dto.LoginUserDTO;
import com.todo.list.controller.dto.QuoteDTO;
import com.todo.list.domain.UserEntity;
import com.todo.list.security.JwtLoginToken;
import com.todo.list.service.api.UserApiService;
import com.todo.list.service.queto.UserQuoteService;
import com.todo.list.service.user.UserService;
import com.todo.list.util.UserUtil;

import io.jsonwebtoken.Claims;

/**
 * 
 * 이 문서는 로그인된 유저가 자기가 작성한 글, 배경, 명언들을 API로 보내줌
 * 
 */
@RestController
public class UserController {

	private static final String SEESION_NAME = "username";
	private static final String CLIENT_SERVER_ADDRESS = "http://127.0.0.1:5501/";

	private UserService userService;
	private UserApiService userApiService;
	private UserQuoteService userQuoteService;

	@Autowired
	private JwtLoginToken jwtLoginToken;

	@Autowired
	public UserController(UserService userService, UserApiService userApiService, UserQuoteService userQuoteService) {
		this.userService = userService;
		this.userApiService = userApiService;
		this.userQuoteService = userQuoteService;
	}

	@PostMapping("/get/user")
	public ResponseEntity<LoginUserDTO> getUser(HttpServletRequest httpServletRequest) {

		String authorization = httpServletRequest.getHeader("authorization");

		if (authorization != null) {
			String username = jwtLoginToken.getUsername(authorization);
			UserEntity userinfo = userApiService.getUserApi(username);

			return new ResponseEntity<LoginUserDTO>(new LoginUserDTO(userinfo), HttpStatus.OK);
		}

		return new ResponseEntity<LoginUserDTO>(HttpStatus.NON_AUTHORITATIVE_INFORMATION);
	}

	@PostMapping("/quote/save")
	public ResponseEntity savetQuote(@RequestBody QuoteDTO quoteDTO, HttpServletRequest httpServletRequest) {
		userQuoteService.quoteInsert(quoteDTO, null);

		String authorization = httpServletRequest.getHeader("authorization");

		if (authorization != null) {
			String username = jwtLoginToken.getUsername(authorization);
			UserEntity userinfo = userApiService.getUserApi(username);

			return new ResponseEntity<LoginUserDTO>(new LoginUserDTO(userinfo), HttpStatus.OK);
		}

		return new ResponseEntity(HttpStatus.OK);
	}

	@PostMapping("/quote/update")
	public ResponseEntity updateQuote(@RequestBody QuoteDTO quoteDTO, HttpServletRequest httpServletRequest) {

		String authorization = httpServletRequest.getHeader("authorization");

		if (authorization != null) {
			String username = jwtLoginToken.getUsername(authorization);
			userQuoteService.quoteUpdate(quoteDTO);

		}
		return new ResponseEntity(HttpStatus.OK);
	}

	@PostMapping("/quote/delete")
	public ResponseEntity deleteQuote(@RequestBody QuoteDTO quoteDTO, HttpServletRequest httpServletRequest) {
		userQuoteService.quoteDelete(quoteDTO);
		String authorization = httpServletRequest.getHeader("authorization");

		if (authorization != null) {
			String username = jwtLoginToken.getUsername(authorization);
			userQuoteService.quoteDelete(quoteDTO);

		}
		return new ResponseEntity(HttpStatus.OK);
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

	@PostMapping("get/valid")
	public Claims getValid(HttpServletRequest httpServletRequest) {

		Enumeration<String> s = httpServletRequest.getHeaderNames();
		String authorization = httpServletRequest.getHeader("authorization");

		Claims body = jwtLoginToken.getUser(authorization);

		return body;
	}
}
