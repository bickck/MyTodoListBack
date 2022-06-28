package com.todo.list.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todo.list.service.todo.UserTodoService;

@RestController
public class TodoTestController {

	@Autowired
	private UserTodoService todoService;
	
	@PostMapping("/test/todo/isPublishTest/{id}")
	public ResponseEntity<?> requestUpdatIsPublishedTest(@PathVariable Long id) {

		todoService.updatePublishedTest(id);

		return new ResponseEntity<String>(HttpStatus.OK);
	}
}
