package com.todo.list.controller.api;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.core.MessageSendingOperations;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todo.list.controller.dto.auth.UserTokenDTO;
import com.todo.list.entity.MessageEntity;
import com.todo.list.service.api.MessageApiService;
import com.todo.list.util.auth.UserAuthToken;

@RestController
@RequestMapping(value = "/message/api")
public class MessageApiController {

	@Autowired
	private SimpMessagingTemplate messagingTemplate;

	@Autowired
	private MessageApiService apiService;

	@GetMapping(value = "/useractions", headers = HttpHeaders.AUTHORIZATION)
	public ResponseEntity<?> requestRecivedMessageByUser(@UserAuthToken UserTokenDTO userTokenDTO) {

		List<MessageEntity> lists = apiService.findReceviedMessageByUserActions(userTokenDTO);

		return new ResponseEntity<List<MessageEntity>>(lists, HttpStatus.OK);
	}

}
