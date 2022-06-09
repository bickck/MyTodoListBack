package com.todo.list.controller;

import java.io.File;
import java.io.IOException;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.todo.list.configs.token.AuthenticationJwtToken;
import com.todo.list.controller.builder.BackGroundImgBuilder;
import com.todo.list.controller.builder.QuoteBuilder;
import com.todo.list.controller.dto.auth.UserTokenDTO;
import com.todo.list.controller.dto.service.QuoteDTO;
import com.todo.list.controller.dto.service.TodoDTO;
import com.todo.list.controller.dto.user.LoginUserDTO;
import com.todo.list.controller.dto.user.UserDTO;
import com.todo.list.entity.UserEntity;
import com.todo.list.entity.TodoEntity;
import com.todo.list.service.api.UserApiService;
import com.todo.list.service.image.ImageService;
import com.todo.list.service.image.UserImageUploadService;
import com.todo.list.service.image.user.UserBackGroundImgService;
import com.todo.list.service.queto.UserQuoteService;
import com.todo.list.service.todo.UserTodoService;
import com.todo.list.service.user.UserService;
import com.todo.list.util.UserUtil;
import com.todo.list.util.auth.UserAuthToken;

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
	private ImageService imaegService;
	private UserTodoService todoService;

	@Autowired
	private AuthenticationJwtToken jwtLoginToken;

	@Autowired
	public UserController(UserService userService, UserApiService userApiService, UserQuoteService userQuoteService) {
		this.userService = userService;
		this.userApiService = userApiService;
		this.userQuoteService = userQuoteService;
		this.imaegService = new UserImageUploadService();
	}

	@PostMapping("/user")
	public ResponseEntity<LoginUserDTO> getUser(@UserAuthToken UserTokenDTO tokenDTO) {

		UserEntity userinfo = userApiService.getUserApi(tokenDTO);

		return new ResponseEntity<LoginUserDTO>(new LoginUserDTO(userinfo), HttpStatus.OK);

	}

	/*
	 * Quote CRUD
	 */

	// save = 1, update = 2, delete = 3
	@PostMapping("/quote/1")
	public ResponseEntity<?> savetUserQuote(@RequestBody QuoteDTO quoteDTO, @UserAuthToken UserTokenDTO tokenDTO) {

		UserEntity user = userApiService.getUserApi(tokenDTO);
		QuoteBuilder builder = new QuoteBuilder();
		builder.setQuote(quoteDTO.getQuote());
		builder.setAuthor(quoteDTO.getAuthor());

		userQuoteService.quoteInsert(builder.builder(), user);

		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PostMapping("/quote/3/{id}")
	public ResponseEntity<?> deleteUserQuote(@PathVariable Long id, @UserAuthToken UserTokenDTO tokenDTO) {

		userQuoteService.quoteDelete(id);

		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	/*
	 * BackGroundImg CRUD
	 */

	@PostMapping("/background/1")
	public ResponseEntity<?> saveUserBackGroundImg(@RequestParam(name = "file") MultipartFile multipartFile,
			@RequestParam(name = "fileName") String fileName, @UserAuthToken UserTokenDTO tokenDTO) throws IOException {

		BackGroundImgBuilder backGroundImgBuilder = new BackGroundImgBuilder();
		backGroundImgBuilder.setFileName(fileName).setMultipartFile(multipartFile).setUserName(tokenDTO.getUsername());
		imaegService.saveImageInDir(backGroundImgBuilder.builder());

		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PostMapping("/background/2/{id}")
	public ResponseEntity<?> updateUserBackGroundImg(@PathVariable Long id) {

		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PostMapping("/background/3/{id}")
	public ResponseEntity<?> deleteUserBackGroundImg(@PathVariable Long id) {
		// imaegService.deleteBackGroundImage(id);

		return new ResponseEntity<>(HttpStatus.OK);
	}

	/*
	 * To Do CRUD
	 */

	@PostMapping("/todo/1")
	public ResponseEntity<?> saveUserTodo(@RequestBody TodoDTO todoDTO, @UserAuthToken UserTokenDTO userTokenDTO) {

		todoService.todoSave(userTokenDTO, todoDTO);

		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	// cache 삭제 후 정보를 다시 입력

	@PostMapping("/todo/2/{id}")
	public ResponseEntity<?> updateUserTodo(@PathVariable Long id, @RequestBody TodoDTO dto,
			@UserAuthToken UserTokenDTO userTokenDTO) {

		todoService.todoUpdate(id, dto);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PostMapping("/todo/3/{id}")
	public ResponseEntity<?> deleteUserTodo(@PathVariable Long id) {

		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PostMapping("/todo/isPublish/{id}")
	public ResponseEntity<?> requestUpdatIsPublished(@PathVariable Long id, @UserAuthToken UserTokenDTO dto) {

		todoService.updatePublished(id, dto.getUsername());

		return new ResponseEntity<String>(HttpStatus.OK);
	}

}
