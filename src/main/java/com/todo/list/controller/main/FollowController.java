package com.todo.list.controller.main;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user/frined")
public class FollowController {

	@PostMapping(value = "/follow/{followerId}")
	public ResponseEntity<?> requestSaveFollow(@PathVariable Long followId) {

		return new ResponseEntity<>(HttpStatus.OK);
	}

	@DeleteMapping(value = "/follow/{followId}")
	public ResponseEntity<?> requestDeleteFollow(@PathVariable Long followId) {
		
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
