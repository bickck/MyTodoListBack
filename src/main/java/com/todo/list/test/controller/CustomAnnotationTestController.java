package com.todo.list.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todo.list.controller.dto.auth.UserTokenDTO;
import com.todo.list.entity.MessageEntity;
import com.todo.list.message.EventMessage;
import com.todo.list.repository.QuoteRepository;
import com.todo.list.test.service.CustomAnnotationTestService;

@RestController
@RequestMapping(value = "/test")
public class CustomAnnotationTestController {

	@Autowired
	private CustomAnnotationTestService annotationTestService;

	@GetMapping(value = "/annotation/messageRecorder")
	public ResponseEntity<?> messageRecorder() {

		annotationTestService.searchUserTokenDTOArgAtEventMessageAnnotation(
				new UserTokenDTO((long) 1, "test name", "test email"), "arg1", "arg2");

		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}

	@GetMapping(value = "/findAll/message")
	public ResponseEntity<?> findAllMessage() {

		List<MessageEntity> lists = annotationTestService.findAllMessage();

		return new ResponseEntity<List<MessageEntity>>(lists, HttpStatus.OK);
	}
}
