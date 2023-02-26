package com.todo.list.controller.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todo.list.controller.ResponseStatusMessage;
import com.todo.list.controller.dto.CommentDTO;
import com.todo.list.controller.dto.auth.UserTokenDTO;
import com.todo.list.entity.TodoCommentEntity;
import com.todo.list.service.main.TodoCommentService;
import com.todo.list.util.auth.UserAuthToken;

@RestController
@RequestMapping(value = "/comment", consumes = { MediaType.APPLICATION_JSON_VALUE }, headers = HttpHeaders.AUTHORIZATION)
public class CommentController {

	private TodoCommentService todoCommentService;

	@Autowired
	public CommentController(TodoCommentService todoCommentService) {
		this.todoCommentService = todoCommentService;
	}

	/**
	 * Todo Id의 코멘트를 저장
	 * 
	 * @param id
	 * @param commentDTO
	 * @param dto
	 * @return status
	 */

	@PostMapping(value = "/{id}")
	public ResponseEntity<?> requestSaveComment(@PathVariable Long id, @RequestBody CommentDTO commentDTO,
			@UserAuthToken UserTokenDTO dto) {

		TodoCommentEntity commentEntity = todoCommentService.saveTodoComment(id, dto, commentDTO);

		if (commentEntity == null) {
			return new ResponseEntity<String>(ResponseStatusMessage.FAILURE, HttpStatus.OK);
		}

		return new ResponseEntity<String>(ResponseStatusMessage.SUCCESS, HttpStatus.OK);
	}

	/**
	 * Comment를 수정
	 * 
	 * @param id
	 * @param commentDTO
	 * @param dto
	 * @return status
	 */

	@PutMapping(value = "/{id}")
	public ResponseEntity<?> requestRecommandUpdate(@PathVariable Long id, @RequestBody CommentDTO commentDTO,
			@UserAuthToken UserTokenDTO dto) {

		todoCommentService.updateTodoComment(id, dto, commentDTO);

		return new ResponseEntity<String>(ResponseStatusMessage.SUCCESS, HttpStatus.OK);
	}

	/**
	 * Comment를 삭제
	 * 
	 * @param id
	 * @param dto
	 * @return status
	 */

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> requestRecommandDelete(@PathVariable Long id, @UserAuthToken UserTokenDTO dto) {

		todoCommentService.deleteTodoComment(id);

		return new ResponseEntity<String>(ResponseStatusMessage.SUCCESS, HttpStatus.OK);
	}
}
