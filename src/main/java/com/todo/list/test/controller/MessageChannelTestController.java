package com.todo.list.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todo.list.redis.entity.MessageChannelEntity;
import com.todo.list.redis.repository.MessageChannelRepository;

@RestController
public class MessageChannelTestController {

	@Autowired
	private MessageChannelRepository messageChannelRepository;

	@GetMapping("/test/messageChannel/lists")
	public ResponseEntity<?> messageChannelList() {

		List<MessageChannelEntity> lists = messageChannelRepository.findAll();

		return new ResponseEntity<List<MessageChannelEntity>>(lists, HttpStatus.OK);
	}
}
