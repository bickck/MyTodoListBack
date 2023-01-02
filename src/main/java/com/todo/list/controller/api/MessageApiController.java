package com.todo.list.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todo.list.controller.dto.auth.UserTokenDTO;
import com.todo.list.service.api.MessageApiService;
import com.todo.list.util.auth.UserAuthToken;

@RestController
@RequestMapping(value = "/message/api")
public class MessageApiController {

	@Autowired
	private MessageApiService apiService;

	@GetMapping(value = "/",consumes = HttpHeaders.AUTHORIZATION)
	public ResponseEntity<?> requestRecivedMessageByUser(@UserAuthToken UserTokenDTO userTokenDTO) {
		
		apiService.findReceviedMessageByUserActions(userTokenDTO);

		return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
	}
}
