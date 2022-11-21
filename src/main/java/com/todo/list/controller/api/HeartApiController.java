package com.todo.list.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.todo.list.controller.dto.HeartDTO;
import com.todo.list.controller.dto.auth.UserTokenDTO;
import com.todo.list.service.api.HeartApiService;
import com.todo.list.util.auth.UserAuthToken;

@RestController
@RequestMapping(value = "/heart/api")
public class HeartApiController {

	@Autowired
	private HeartApiService heartApiService;

	@PostMapping("/todo/{id}")
	@ResponseBody
	public ResponseEntity<?> requestTodoHeartExists(@PathVariable Long id, @UserAuthToken UserTokenDTO userTokenDTO) {
		
		HeartDTO result = heartApiService.existsTodoHeart(id,userTokenDTO);
		
		return new ResponseEntity<HeartDTO>(result, HttpStatus.OK);
	}

	@PostMapping("/quote/{id}")
	@ResponseBody
	public ResponseEntity<?> requestQuoteHeartExists(@PathVariable Long id,@UserAuthToken UserTokenDTO userTokenDTO) {
		
		HeartDTO result = heartApiService.existsQuoteHeart(id,userTokenDTO);
		
		
		return new ResponseEntity<HeartDTO>(result, HttpStatus.OK);

	}

}
