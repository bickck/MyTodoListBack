package com.todo.list.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todo.list.controller.ResponseStatus;
import com.todo.list.controller.dto.CommentDTO;
import com.todo.list.controller.dto.auth.UserTokenDTO;
import com.todo.list.entity.TodoCommentEntity;
import com.todo.list.service.user.TodoCommentService;
import com.todo.list.service.user.TodoService;
import com.todo.list.util.auth.UserAuthToken;

@RestController
@RequestMapping(value = "/comment", consumes = { MediaType.APPLICATION_JSON_VALUE })
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

	@PostMapping(value = "/comment/add/{id}")
	public ResponseEntity<?> requestSaveComment(@PathVariable Long id, @RequestBody CommentDTO commentDTO,
			@UserAuthToken UserTokenDTO dto) {

		TodoCommentEntity commentEntity = todoCommentService.saveTodoComment(id, dto, commentDTO);

		if (commentEntity == null) {

		}

		return new ResponseEntity<String>(ResponseStatus.SUCCESS, HttpStatus.OK);
	}

	/**
	 * Comment를 수정
	 * 
	 * @param id
	 * @param commentDTO
	 * @param dto
	 * @return status
	 */

	@PostMapping(value = "/comment/update/{id}")
	public ResponseEntity<?> requestRecommandUpdate(@PathVariable Long id, @RequestBody CommentDTO commentDTO,
			@UserAuthToken UserTokenDTO dto) {

		todoCommentService.updateTodoComment(id, dto, commentDTO);

		return new ResponseEntity<String>(ResponseStatus.SUCCESS, HttpStatus.OK);
	}

	/**
	 * Comment를 삭제
	 * 
	 * @param id
	 * @param dto
	 * @return status
	 */

	@PostMapping(value = "/comment/delete/{id}")
	public ResponseEntity<?> requestRecommandDelete(@PathVariable Long id, @UserAuthToken UserTokenDTO dto) {

		todoCommentService.deleteTodoComment(id);

		return new ResponseEntity<String>(ResponseStatus.SUCCESS, HttpStatus.OK);
	}
}
