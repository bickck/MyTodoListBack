package com.todo.list.controller.user;

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

import com.todo.list.configs.token.AuthenticationJwt;
import com.todo.list.controller.builder.BackGroundImgBuilder;
import com.todo.list.controller.builder.QuoteBuilder;
import com.todo.list.controller.dto.auth.UserTokenDTO;
import com.todo.list.controller.dto.service.QuoteDTO;
import com.todo.list.controller.dto.service.TodoDTO;
import com.todo.list.controller.dto.user.LoginUserDTO;
import com.todo.list.controller.dto.user.UserDTO;
import com.todo.list.entity.UserEntity;
import com.todo.list.entity.UserTodoEntity;
import com.todo.list.service.api.UserApiService;
import com.todo.list.service.image.ImageService;
import com.todo.list.service.image.UserImageUploadService;
import com.todo.list.service.image.user.UserImageService;
import com.todo.list.service.user.UserQuoteService;
import com.todo.list.service.user.UserService;
import com.todo.list.service.user.UserTodoService;
import com.todo.list.util.UserUtil;
import com.todo.list.util.auth.UserAuthToken;

import io.jsonwebtoken.Claims;
import lombok.extern.java.Log;


/**
 * 
 * 해당 유저의 데이터를 가지고 있는 클래스
 * 
 */

@RestController
@RequestMapping(value = "/user/manage")
public class UserController {

	private static final String SEESION_NAME = "username";
	private static final String CLIENT_SERVER_ADDRESS = "http://127.0.0.1:5501/";

	private UserService userService;

	private UserQuoteService userQuoteService;
	private ImageService imaegService;
	private UserTodoService todoService;

	@Autowired
	private AuthenticationJwt jwtLoginToken;

	@Autowired
	public UserController(UserService userService, UserApiService userApiService, UserQuoteService userQuoteService) {
		this.userService = userService;
		this.userQuoteService = userQuoteService;
		this.imaegService = new UserImageUploadService();
	}

	@PostMapping("/update/intro/{id}")
	public ResponseEntity<?> updateUser(@PathVariable Long id,@RequestBody UserDTO userDTO) {

		UserEntity userEntity = new UserEntity();
		userEntity.setId(id);
		userEntity.setIntroComment(userDTO.getIntroComment());
		userService.userUpdate(userEntity);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	// User identify number

	@PostMapping("/delete/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable Long id) {

		userService.userDelete(id);

		return new ResponseEntity<>(HttpStatus.OK);
	}

	// change password

	@PostMapping("/changePassword/{id}")
	public ResponseEntity<?> changePassword(@PathVariable Long id) {
		// imaegService.deleteBackGroundImage(id);

		return new ResponseEntity<>(HttpStatus.OK);
	}

}
