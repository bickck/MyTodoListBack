package com.todo.list.controller.user;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
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
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.todo.list.configs.token.AuthenticationJwtProvider;
import com.todo.list.controller.ResponseStatus;
import com.todo.list.controller.builder.BackGroundImgBuilder;
import com.todo.list.controller.builder.QuoteBuilder;
import com.todo.list.controller.dto.QuoteDTO;
import com.todo.list.controller.dto.TodoDTO;
import com.todo.list.controller.dto.auth.UserTokenDTO;
import com.todo.list.controller.dto.user.LoginUserDTO;
import com.todo.list.controller.dto.user.UserDTO;
import com.todo.list.entity.UserEntity;
import com.todo.list.entity.UserImageEntity;
import com.todo.list.entity.TodoEntity;
import com.todo.list.service.api.UserApiService;
import com.todo.list.service.image.ImageUploadService;
import com.todo.list.service.image.upload.UserImageUploadService;
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
@RequestMapping(value = "/user", headers = HttpHeaders.AUTHORIZATION)
public class UserController implements ResponseStatus {

	private UserService userService;

	@Autowired
	public UserController(UserService userService, UserApiService userApiService, QuoteService userQuoteService,
			AuthenticationJwtProvider jwtLoginToken, UserImageUploadService imaegService) {
		this.userService = userService;
	}

	/**
	 * 
	 * @param id
	 * @param userDTO
	 * @return
	 */

	@PutMapping("/intro/comment")
	public ResponseEntity<?> updateUser(@RequestBody UserDTO userDTO, @UserAuthToken UserTokenDTO userTokenDTO) {

		String introComment = userDTO.getIntroComment();

		if (introComment == null || introComment.isEmpty()) {
			return new ResponseEntity<>(ResponseStatus.FAILURE, HttpStatus.OK);
		}

		userService.updateUserIntroComment(introComment, userTokenDTO);

		return new ResponseEntity<>(ResponseStatus.SUCCESS, HttpStatus.OK);
	}

	/**
	 * 
	 * @param id
	 * @return
	 */

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable Long id, @UserAuthToken UserTokenDTO userTokenDTO) {

		userService.userDelete(id);

		return new ResponseEntity<>(ResponseStatus.SUCCESS, HttpStatus.OK);
	}

	/**
	 * 
	 * @param id
	 * @param userDTO
	 * @return
	 */

	@PutMapping("/changePassword/{id}")
	public ResponseEntity<?> changePassword(@PathVariable Long id, UserDTO userDTO) {

		userService.changeUserPassword(id, userDTO);

		return new ResponseEntity<>(ResponseStatus.SUCCESS, HttpStatus.OK);
	}

	/**
	 * 
	 * @param id
	 * @param userDTO
	 * @return
	 */

	@PutMapping("/intro")
	public ResponseEntity<?> updateUserIntro(@RequestBody UserDTO userDTO, @UserAuthToken UserTokenDTO userTokenDTO) {

		userService.userUpdate(userDTO, userTokenDTO);

		return new ResponseEntity<>(ResponseStatus.SUCCESS, HttpStatus.OK);
	}

	/**
	 * 
	 * @param userDTO
	 * @param file
	 * @return
	 */

	@PutMapping(value = "/intro/image", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.ALL_VALUE })
	public ResponseEntity<?> updateUserImage(@UserAuthToken UserTokenDTO userDTO,
			@RequestParam(value = "file") MultipartFile file) {

		UserImageEntity entity = userService.updateUserIntroImage(userDTO.getId(), file);

		if (entity == null) {
			return new ResponseEntity<>(ResponseStatus.FAILURE, HttpStatus.OK);
		}

		return new ResponseEntity<>(ResponseStatus.SUCCESS, HttpStatus.OK);
	}

	/**
	 * 
	 * @param userDTO
	 * @return
	 */

	@DeleteMapping(value = "/intro/image")
	public ResponseEntity<?> deleteUserImage(@UserAuthToken UserTokenDTO userDTO) {

		boolean result;

		try {
			result = userService.deleteUserIntroImage(userDTO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<>(ResponseStatus.FAILURE, HttpStatus.OK);
		}

		if (!result) {
			return new ResponseEntity<>(ResponseStatus.FAILURE, HttpStatus.OK);

		}

		return new ResponseEntity<>(ResponseStatus.SUCCESS, HttpStatus.OK);
	}

}
