package com.todo.list.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todo.list.controller.dto.auth.UserTokenDTO;
import com.todo.list.repository.mapper.QuoteMapper;
import com.todo.list.repository.mapper.TodoMapper;
import com.todo.list.service.api.UserLikeApiService;
import com.todo.list.util.auth.UserAuthToken;

/**
 * 
 * @author 3d193
 *
 */

@RestController
@RequestMapping(value = "/user/like")
public class UserLikeApiController {

	@Autowired
	private UserLikeApiService userLikeApiService;

	/**
	 * 
	 */

	@PostMapping(value = "/todos")
	public ResponseEntity<?> requestUserLikeTodoLists(
			@PageableDefault(size = 5, direction = Direction.ASC) Pageable pageable,
			@UserAuthToken UserTokenDTO userTokenDTO) {

		Page<TodoMapper> page = userLikeApiService.userLikeTodo(userTokenDTO, pageable);

		return new ResponseEntity<Page<TodoMapper>>(page, HttpStatus.OK);
	}

	/**
	 * 
	 */

	@PostMapping(value = "/quotes")
	public ResponseEntity<?> requestUserLikeQuoteLists(
			@PageableDefault(size = 5, direction = Direction.ASC) Pageable pageable,
			@UserAuthToken UserTokenDTO userTokenDTO) {

		Page<QuoteMapper> page = userLikeApiService.userLikeQuote(userTokenDTO, pageable);

		return new ResponseEntity<Page<QuoteMapper>>(page, HttpStatus.OK);
	}

}
