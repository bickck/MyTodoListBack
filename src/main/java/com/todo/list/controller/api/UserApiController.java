package com.todo.list.controller.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.todo.list.controller.dto.auth.UserTokenDTO;
import com.todo.list.repository.mapper.QuoteMapper;
import com.todo.list.repository.mapper.TodoMapper;
import com.todo.list.repository.mapper.UserIntroMapper;
import com.todo.list.service.api.UserApiService;
import com.todo.list.service.user.UserService;
import com.todo.list.util.auth.UserAuthToken;
import com.todo.list.util.auth.provider.AuthenticationJwtProvider;

/**
 * 로그인이 되었을 시 유저 브라우저에 있는 토큰 정보를 가지고 데이터를 제공하는 파일
 * 
 */
//@CrossOrigin(originPatterns = "http://localhost:5501",exposedHeaders = "")
@RestController
//@RequestMapping(value = "/user/api")
public class UserApiController {

	private UserService userService;
	private UserApiService userApiService;

	@Autowired
	private AuthenticationJwtProvider jwtLoginToken;

	@Autowired
	public UserApiController(UserService userService, UserApiService userApiService) {
		this.userService = userService;
		this.userApiService = userApiService;

	}

	/**
	 * 
	 * 유저의 Intro 정보 가져오기
	 * 
	 * header에 유저의 Jwt으로 판별
	 * 
	 * return username, userIntro, userImagePath
	 */

	@ResponseBody
	@PostMapping(value = "/user/api/intro")
	public ResponseEntity<?> getUserIntroInfo(@UserAuthToken UserTokenDTO userTokenDTO) {

		Long id = userTokenDTO.getId();
		String email = userTokenDTO.getEmail();
		UserIntroMapper userIntroMapper = userApiService.getUserIntroDetailsApi(id, email);

		return new ResponseEntity<UserIntroMapper>(userIntroMapper, HttpStatus.OK);
	}
	
	/**
	 * 
	 * 유저의 Intro 정보 가져오기
	 * 
	 * header에 유저의 Jwt으로 판별
	 * 
	 * return username, userIntro, userImagePath
	 */

	@ResponseBody
	@GetMapping(value = "/api/intro/{username}")
	public ResponseEntity<?> getUserIntroInfo(@PathVariable String username) {

		
		UserIntroMapper userIntroMapper = userApiService.getUserIntroDetailsApi(username);

		return new ResponseEntity<UserIntroMapper>(userIntroMapper, HttpStatus.OK);
	}

	/**
	 * 
	 * 유저의 Todo 모든 정보 가져오기
	 */

	@ResponseBody
	@PostMapping("/user/api/todos")
	public ResponseEntity<?> getUserApiTodos(@PageableDefault(size = 5, direction = Direction.ASC) Pageable pageable,
			@UserAuthToken UserTokenDTO userTokenDTO) {

		Page<TodoMapper> page = userApiService.getUserTodos(userTokenDTO, pageable);

		return new ResponseEntity<Page<TodoMapper>>(page, HttpStatus.OK);
	}

	/**
	 * 
	 * 유저의 Quote 모든 정보 가져오기
	 */

	@PostMapping("/user/api/quotes")
	public ResponseEntity<?> getUserApiQuotes(@PageableDefault(size = 5, direction = Direction.ASC) Pageable pageable,
			@UserAuthToken UserTokenDTO userTokenDTO) {

		Page<QuoteMapper> page = userApiService.getUserQuotes(userTokenDTO, pageable);

		return new ResponseEntity<Page<QuoteMapper>>(page, HttpStatus.OK);
	}
}
