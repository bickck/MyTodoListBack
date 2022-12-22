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

import com.todo.list.configs.token.AuthenticationJwtProvider;
import com.todo.list.controller.dto.auth.UserTokenDTO;
import com.todo.list.repository.mapper.QuoteMapper;
import com.todo.list.repository.mapper.TodoMapper;
import com.todo.list.repository.mapper.UserIntroMapper;
import com.todo.list.service.api.UserApiService;
import com.todo.list.service.user.UserService;
import com.todo.list.util.auth.UserAuthToken;

/**
 * 로그인이 되었을 시 유저 브라우저에 있는 토큰 정보를 가지고 데이터를 제공하는 파일
 * 
 */
//@CrossOrigin(originPatterns = "http://localhost:5501",exposedHeaders = "")
@RestController
@RequestMapping(value = "/user/api")
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
	@PostMapping(value = "/intro")
	public ResponseEntity<?> getUserIntroInfo(@UserAuthToken UserTokenDTO userTokenDTO) {

		Long id = userTokenDTO.getId();
		String email = userTokenDTO.getEmail();
		UserIntroMapper userIntroMapper = userApiService.getUserIntroDetailsApi(id, email);

		return new ResponseEntity<UserIntroMapper>(userIntroMapper, HttpStatus.OK);
	}

	/**
	 * 
	 * 유저의 Todo 모든 정보 가져오기
	 */

	@ResponseBody
	@PostMapping("/todos")
	public ResponseEntity<?> getUserApiTodos(@PageableDefault(size = 5, direction = Direction.ASC) Pageable pageable,
			@UserAuthToken UserTokenDTO userTokenDTO) {

		Page<TodoMapper> page = userApiService.getUserTodos(userTokenDTO, pageable);

		return new ResponseEntity<Page<TodoMapper>>(page, HttpStatus.OK);
	}

	/**
	 * 
	 * 유저의 Quote 모든 정보 가져오기
	 */

	@PostMapping("/quotes")
	public ResponseEntity<?> getUserApiQuotes(@PageableDefault(size = 5, direction = Direction.ASC) Pageable pageable,
			@UserAuthToken UserTokenDTO userTokenDTO) {

		Page<QuoteMapper> page = userApiService.getUserquotes(userTokenDTO, pageable);

		return new ResponseEntity<Page<QuoteMapper>>(page, HttpStatus.OK);
	}

	/**
	 * 
	 * 유저의 Quote 모든 정보 가져오기
	 */

//	@PostMapping("/quotes")
//	public ResponseEntity<PageQuoteDTO> getUserApiQuotes(
//			@PageableDefault(size = 8, direction = Direction.ASC) Pageable pageable) {
//		UserTokenDTO userTokenDTO = new UserTokenDTO((long) 1, "username0");
//
//		Page<QuoteEntity> entities = userApiService.getUserquotes(userTokenDTO, pageable);
//		PageQuoteBuilder builder = new PageQuoteBuilder();
//		builder.setLists(entities.getContent());
//		builder.setNumber(entities.getNumber());
//		builder.setNumberOfElements(entities.getNumberOfElements());
//		builder.setPageable(entities.getPageable());
//		builder.setSize(entities.getSize());
//		builder.setTotalPages(entities.getTotalPages());
//		builder.setTotalElements(entities.getTotalElements());
//		return new ResponseEntity<PageQuoteDTO>(builder.builder(), HttpStatus.OK);
//	}
//	

//	@GetMapping("/{id}")
//	public ResponseEntity<List<BackGroundDTO>> getUserApiBackGroundsByid(@PathVariable Integer id,
//			@UserAuthToken UserTokenDTO userTokenDTO) {
//		PageRequest pageRequest = PageRequest.of(id, 10, Sort.Direction.ASC, "id");
//
//		List<BackGroundDTO> list = null;
//		userApiService.getUserBackGrounds(userTokenDTO, pageRequest);
//		return new ResponseEntity<List<BackGroundDTO>>(list, HttpStatus.OK);
//	}

	/**
	 * 
	 * @param id
	 * @param userTokenDTO
	 * @return
	 */
//	@GetMapping("/recommand")
//	public ResponseEntity<List<BackGroundDTO>> getUserRecommands(@PathVariable Integer id,
//			@UserAuthToken UserTokenDTO userTokenDTO) {
//		PageRequest pageRequest = PageRequest.of(id, 10, Sort.Direction.ASC, "id");
//
//		List<BackGroundDTO> list = null;
//		userApiService.getUserBackGrounds(userTokenDTO, pageRequest);
//		return new ResponseEntity<List<BackGroundDTO>>(list, HttpStatus.OK);
//	}

//	@PostMapping("/backgrounds")
//	public ResponseEntity<List<BackGroundDTO>> getUserApiBackGrounds(@UserAuthToken UserTokenDTO userTokenDTO,
//			@PageableDefault(size = 10, direction = Direction.ASC) Pageable pageable) {
//
//		Page<UserBackGroundImageEntity> entities = userApiService.getUserBackGrounds(userTokenDTO, pageable);
//
//		return new ResponseEntity<List<BackGroundDTO>>(null, HttpStatus.OK);
//	}
}
