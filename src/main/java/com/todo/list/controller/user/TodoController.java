package com.todo.list.controller.user;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.text.AbstractDocument.Content;
import javax.validation.Valid;
import javax.validation.constraints.Size;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.todo.list.controller.ResponseStatus;
import com.todo.list.controller.builder.QuoteBuilder;
import com.todo.list.controller.builder.TodoBuilder;
import com.todo.list.controller.builder.page.PageTodoBuilder;
import com.todo.list.controller.dto.CommentDTO;
import com.todo.list.controller.dto.QuoteDTO;
import com.todo.list.controller.dto.TodoDTO;
import com.todo.list.controller.dto.auth.UserTokenDTO;
import com.todo.list.controller.dto.page.PageTodoDTO;
import com.todo.list.entity.TodoCommentEntity;
import com.todo.list.entity.TodoEntity;
import com.todo.list.entity.UserEntity;
import com.todo.list.service.api.UserApiService;
import com.todo.list.service.user.QuoteService;
import com.todo.list.service.user.TodoService;
import com.todo.list.util.auth.UserAuthToken;

/**
 * 
 * 해당 유저의 Todo 데이터를 저장,수정,삭제를 제공하는 클래스
 * 
 */

@RestController
@RequestMapping(value = "/user/todo/manage")
public class TodoController {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	private TodoService userTodoService;

	@Autowired
	public TodoController(TodoService userTodoService) {
		this.userTodoService = userTodoService;
	}

	/**
	 * 
	 * @param todoDTO
	 * @param userTokenDTO
	 * @return status
	 * @throws IOException
	 */

	@PostMapping(value = "/save", consumes = {
			MediaType.ALL_VALUE, 
			MediaType.MULTIPART_FORM_DATA_VALUE,
			MediaType.APPLICATION_FORM_URLENCODED_VALUE ,
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> saveUserTodo(@RequestPart(value = "todos") String todoString,
			@RequestPart(value = "files",required = false) MultipartFile[] todoImages, @UserAuthToken UserTokenDTO userTokenDTO)
			throws IOException {

		TodoDTO todoDTO = new ObjectMapper().readValue(todoString, TodoDTO.class);
		// 이미지 제약 조건
//		if (todoImages.length > 2 && todoImages = null) {
//			return new ResponseEntity<String>(ResponseStatus.FALIURE, HttpStatus.OK);
//		}
		
		if(todoImages != null) {
			if(todoImages.length > 2) {
				return new ResponseEntity<String>(ResponseStatus.FALIURE, HttpStatus.OK);
			}
		}
		System.out.println(todoImages);

		userTodoService.saveTodo(userTokenDTO, todoDTO, todoImages);

		return new ResponseEntity<String>(ResponseStatus.SUCCESS, HttpStatus.OK);
	}

	/**
	 *
	 * @param id
	 * @param todoDTO
	 * @param userTokenDTO
	 * @return status
	 */

	@PostMapping("/update/{id}")
	public ResponseEntity<?> updateUserTodo(@PathVariable Long id, @RequestBody TodoDTO todoDTO,
			@UserAuthToken UserTokenDTO userTokenDTO) {

		userTodoService.updateTodo(id, todoDTO);

		return new ResponseEntity<String>(ResponseStatus.SUCCESS, HttpStatus.OK);
	}

	/**
	 * 
	 * @param id
	 * @param userTokenDTO
	 * @return status
	 */

	@PostMapping("/delete/{id}")
	public ResponseEntity<?> deleteUserTodo(@PathVariable Long id, @UserAuthToken UserTokenDTO userTokenDTO) {

		userTodoService.deleteTodo(id);
		return new ResponseEntity<String>(ResponseStatus.SUCCESS, HttpStatus.OK);
	}

	/**
	 * 
	 * @param id
	 * @param userTokenDTO
	 * @return status
	 */

	@PostMapping("/heart/add/{id}")
	public ResponseEntity<?> todoCommentHeartAdd(@PathVariable Long id, @UserAuthToken UserTokenDTO userTokenDTO) {

		userTodoService.addTodoHeart(id);

		return new ResponseEntity<String>(ResponseStatus.SUCCESS, HttpStatus.OK);
	}

}
