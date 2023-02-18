package com.todo.list.controller.main;

/**
 * 
 * 프론트의 데이터 값을 미리 체크하는 클래스
 * */

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todo.list.controller.ResponseStatusMessage;
import com.todo.list.controller.dto.user.UserDTO;
import com.todo.list.service.ValidService;

@RestController
@RequestMapping(value = "/valid")
public class ValidController implements ResponseStatusMessage {

	@Autowired
	private ValidService validService;

	@PostMapping("/emailduplication")
	public ResponseEntity<String> validEmailCheck(@RequestBody UserDTO emailDuplication) {
		
		String email = emailDuplication.getEmail();
		if(email.equals("")) {
			return new ResponseEntity<String>(ResponseStatusMessage.FAILURE, HttpStatus.OK);
		}
		System.out.println(!validService.emailDuplicationCheck(email));
		if (validService.emailDuplicationCheck(email)) {
			return new ResponseEntity<String>(ResponseStatusMessage.FAILURE, HttpStatus.OK);
		}

		return new ResponseEntity<String>(ResponseStatusMessage.SUCCESS, HttpStatus.OK);
	}

	@PostMapping("/valid/token")
	public String refreshToken(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

		String authorization = httpServletRequest.getHeader("authorization");

		return "1234";
	}
}
