package com.todo.list.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todo.list.controller.dto.auth.UserTokenDTO;
import com.todo.list.service.api.UserFollowApiService;
import com.todo.list.util.auth.UserAuthToken;

@RestController
@RequestMapping(value = "/friend/api")
public class UserFollowApiController {
	
	@Autowired
	private UserFollowApiService userFollowApiService;

	
	@GetMapping(value="/follower")
	public ResponseEntity<?> requestUserFollower(@UserAuthToken UserTokenDTO userTokenDTO) {
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping(value ="/following")
	public ResponseEntity<?> requestUserFollowing(@UserAuthToken UserTokenDTO userTokenDTO) {
		
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
