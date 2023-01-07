package com.todo.list.test.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebSocketTestController {

	
	@GetMapping("/test/socket")
	public ResponseEntity<?> socketTest() {
		
		return new ResponseEntity<String>("SUCCESS",HttpStatus.OK);
	}
	
	@MessageMapping("/hello")
	public String messageTest(String message) {
		return message + " - good";
	}
}
