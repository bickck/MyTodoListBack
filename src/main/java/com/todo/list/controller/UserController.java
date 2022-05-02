package com.todo.list.controller;

import java.sql.Date;
import java.util.Enumeration;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.todo.list.controller.dto.LoginUserDTO;
import com.todo.list.controller.dto.QuoteDTO;
import com.todo.list.controller.dto.UserDTO;
import com.todo.list.controller.dto.UserTokenDTO;
import com.todo.list.domain.UserEntity;
import com.todo.list.security.AuthenticationJwtToken;
import com.todo.list.service.api.UserApiService;
import com.todo.list.service.queto.UserQuoteService;
import com.todo.list.service.user.UserService;
import com.todo.list.util.UserUtil;
import com.todo.list.util.aop.TokenValidator;

import io.jsonwebtoken.Claims;
import lombok.extern.java.Log;

/**
 * 
 * 이 문서는 로그인된 유저가 자기가 작성한 글, 배경, 명언들을 API로 보내줌
 * 
 */
@RestController
@RequestMapping(value = "/user/manage")
public class UserController {

	private static final String SEESION_NAME = "username";
	private static final String CLIENT_SERVER_ADDRESS = "http://127.0.0.1:5501/";

	private UserService userService;
	private UserApiService userApiService;
	private UserQuoteService userQuoteService;

	@Autowired
	private AuthenticationJwtToken jwtLoginToken;

	@Autowired
	public UserController(UserService userService, UserApiService userApiService, UserQuoteService userQuoteService) {
		this.userService = userService;
		this.userApiService = userApiService;
		this.userQuoteService = userQuoteService;
	}

	@PostMapping("/user")
	public ResponseEntity<LoginUserDTO> getUser(@TokenValidator UserTokenDTO tokenDTO) {

		UserEntity userinfo = userApiService.getUserApi(tokenDTO);

		return new ResponseEntity<LoginUserDTO>(HttpStatus.OK);

	}

	// save = 1, update = 2, delete = 3
	@PostMapping("/quote/1")
	public ResponseEntity savetUserQuote(@RequestBody QuoteDTO quoteDTO, @TokenValidator UserTokenDTO tokenDTO) {

		UserEntity user = userApiService.getUserApi(tokenDTO);
		userQuoteService.quoteInsert(quoteDTO, null);

		return new ResponseEntity(HttpStatus.OK);
	}

	@PostMapping("/quote/3/{id}")
	public ResponseEntity deleteUserQuote(@PathVariable Long id, @TokenValidator UserTokenDTO tokenDTO) {

		userQuoteService.quoteDelete(id);

		return new ResponseEntity(HttpStatus.OK);
	}

	@PostMapping("/background/1")
	public ResponseEntity saveUserBackGroundImg(MultipartFile multipartFile) {
		Resource resource = multipartFile.getResource();
		String path = multipartFile.getName();
		String originFileName = multipartFile.getOriginalFilename();

		return new ResponseEntity(HttpStatus.OK);
	}

	@PostMapping("/background/2/{id}")
	public ResponseEntity updateUserBackGroundImg(@PathVariable Long id) {

		return new ResponseEntity(HttpStatus.OK);
	}

	@PostMapping("/background/3/{id}")
	public ResponseEntity deleteUserBackGroundImg(@PathVariable Long id) {

		return new ResponseEntity(HttpStatus.OK);
	}

}
