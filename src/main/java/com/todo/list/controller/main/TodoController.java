package com.todo.list.controller.main;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.todo.list.controller.ResponseStatus;
import com.todo.list.controller.dto.TodoDTO;
import com.todo.list.controller.dto.auth.UserTokenDTO;

import com.todo.list.entity.base.Publish;

import com.todo.list.service.user.TodoService;

import com.todo.list.util.auth.UserAuthToken;


/**
 * 
 * 해당 유저의 Todo 데이터를 저장,수정,삭제를 제공하는 클래스
 * 
 */

@RestController
@RequestMapping(value = "/user/todo", headers = HttpHeaders.AUTHORIZATION)
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

	@PostMapping(value = "/post", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE,
			MediaType.ALL_VALUE }, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> requestSaveUserTodoForMultiPartForm(@RequestPart(value = "todos") String todoString,
			@RequestPart(value = "files", required = false) MultipartFile[] todoImages,
			@UserAuthToken UserTokenDTO userTokenDTO) throws IOException {

		Publish defaultPublishValue = Publish.PUBLISH;
		TodoDTO todoDTO = new ObjectMapper().readValue(todoString, TodoDTO.class);

		// 이미지가 2개 이상 넘어갈 경우
		if (todoImages != null && todoImages.length > 2) {
			return new ResponseEntity<String>(ResponseStatus.FAILURE, HttpStatus.OK);
		}

		if (todoDTO.getIsPublish().equals("private") || todoDTO.getIsPublish().equals("PRIVATE")) {
			defaultPublishValue = Publish.PRIVATE;
		}

		userTodoService.saveTodo(userTokenDTO, todoDTO, todoImages);

		return new ResponseEntity<String>(ResponseStatus.SUCCESS, HttpStatus.OK);
	}


	/**
	 *
	 * @param id
	 * @param todoDTO
	 * @param userTokenDTO
	 * @return status
	 * @throws Exception 
	 * @throws JsonProcessingException
	 * @throws JsonMappingException
	 */

	@PutMapping(value = "/post/{id}", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE,
			MediaType.ALL_VALUE }, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> requestUpdateUserTodoForMultipart(@PathVariable(value = "id") Long id,
			@RequestPart(value = "todos") String todoString,
			@RequestPart(value = "files", required = false) MultipartFile[] todoImages,
			@UserAuthToken UserTokenDTO userTokenDTO) throws Exception {

		TodoDTO todoDTO = new ObjectMapper().readValue(todoString, TodoDTO.class);
		Publish publish = Publish.PUBLISH;

		if (todoDTO.getIsPublish().equals("private")) {
			publish = Publish.PRIVATE;
		}

		if (todoImages != null && todoImages.length > 2) {

			return new ResponseEntity<String>(ResponseStatus.FAILURE, HttpStatus.OK);

		}

		todoDTO.setHeart((long) 0);

		userTodoService.updateTodo(id, todoDTO, todoImages);

		return new ResponseEntity<String>(ResponseStatus.SUCCESS, HttpStatus.OK);
	}

	@PutMapping(value = "/publish/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateTodoPublished(@PathVariable(value = "id") Long id,
			@UserAuthToken UserTokenDTO userTokenDTO) {

		userTodoService.updateTodoPublished(id, userTokenDTO.getUsername());

		return new ResponseEntity<String>(ResponseStatus.SUCCESS, HttpStatus.OK);

	}

	/**
	 * 
	 * @param id
	 * @param userTokenDTO
	 * @return status
	 * @throws Exception 
	 */

	@DeleteMapping("/{id}")
	public ResponseEntity<?> requestDeleteUserTodo(@PathVariable Long id, @UserAuthToken UserTokenDTO userTokenDTO) throws Exception {

		try {
			userTodoService.deleteTodo(id);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<String>(ResponseStatus.SUCCESS, HttpStatus.OK);
	}

	/**
	 * 
	 * @param id
	 * @param userTokenDTO
	 * @return status
	 */

//	@PostMapping("/heart/add/{id}")
//	public ResponseEntity<?> requestTodoCommentHeartAdd(@PathVariable Long id, @UserAuthToken UserTokenDTO userTokenDTO) {
//
//		userTodoService.addTodoHeart(id);
//
//		return new ResponseEntity<String>(ResponseStatus.SUCCESS, HttpStatus.OK);
//	}

}
