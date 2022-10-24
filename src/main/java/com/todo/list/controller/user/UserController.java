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
import com.todo.list.controller.dto.QuoteDTO;
import com.todo.list.controller.dto.TodoDTO;
import com.todo.list.controller.dto.auth.UserTokenDTO;
import com.todo.list.controller.dto.user.LoginUserDTO;
import com.todo.list.controller.dto.user.UserDTO;
import com.todo.list.entity.UserEntity;
import com.todo.list.entity.TodoEntity;
import com.todo.list.service.api.UserApiService;
import com.todo.list.service.image.ImageService;
import com.todo.list.service.image.UserImageUploadService;
import com.todo.list.service.image.user.UserImageService;
import com.todo.list.service.user.QuoteService;
import com.todo.list.service.user.UserService;
import com.todo.list.service.user.TodoService;
import com.todo.list.util.UserUtil;
import com.todo.list.util.auth.UserAuthToken;

import io.jsonwebtoken.Claims;
import lombok.extern.java.Log;

/**
 * 
 * 유저의 데이터를 저장,수정,삭제를 제공하는 클래스
 * 
 * parm : id -> user identify
 */

@RestController
@RequestMapping(value = "/user/manage")
public class UserController {

//	private static final String SEESION_NAME = "username";
//	private static final String CLIENT_SERVER_ADDRESS = "http://127.0.0.1:5501/";

	private UserService userService;
	private QuoteService userQuoteService;
	private ImageService imaegService;
	private TodoService todoService;
	private AuthenticationJwt jwtLoginToken;

	@Autowired
	public UserController(UserService userService, UserApiService userApiService, QuoteService userQuoteService,
			AuthenticationJwt jwtLoginToken) {
		this.userService = userService;
		this.userQuoteService = userQuoteService;
		this.jwtLoginToken = jwtLoginToken;
		this.imaegService = new UserImageUploadService();
	}

	/**
	 * 
	 * @param id
	 * @param userDTO
	 * @return
	 */

	@PostMapping("/update/intro/{id}")
	public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {

		UserEntity userEntity = new UserEntity();
		userEntity.setId(id);	
		userEntity.setIntroComment(userDTO.getIntroComment());
		userService.userUpdate(userEntity);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	/**
	 * 
	 * @param id
	 * @return
	 */

	@PostMapping("/delete/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable Long id) {

		userService.userDelete(id);

		return new ResponseEntity<>(HttpStatus.OK);
	}

	/**
	 * 
	 * @param id
	 * @param userDTO
	 * @return
	 */

	@PostMapping("/changePassword/{id}")
	public ResponseEntity<?> changePassword(@PathVariable Long id, UserDTO userDTO) {

		userService.changeUserPassword(id, userDTO);

		return new ResponseEntity<>(HttpStatus.OK);
	}

}
