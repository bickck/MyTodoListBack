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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import com.todo.list.controller.ResponseStatus;
import com.todo.list.controller.dto.user.UserDTO;
import com.todo.list.service.ValidService;
import com.todo.list.util.UserUtil;

@RestController
@RequestMapping(value = "/valid")
public class ValidController implements ResponseStatus {

	@Autowired
	private ValidService validService;

	@PostMapping("/emailduplication")
	public ResponseEntity<String> validEmailCheck(@RequestBody UserDTO emailDuplication) {
		
		String email = emailDuplication.getEmail();
		if(email.equals("")) {
			return new ResponseEntity<String>(ResponseStatus.FAILURE, HttpStatus.OK);
		}
		System.out.println(!validService.emailDuplicationCheck(email));
		if (validService.emailDuplicationCheck(email)) {
			return new ResponseEntity<String>(ResponseStatus.FAILURE, HttpStatus.OK);
		}

		return new ResponseEntity<String>(ResponseStatus.SUCCESS, HttpStatus.OK);
	}

	@PostMapping("/valid/token")
	public String refreshToken(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

		String authorization = httpServletRequest.getHeader("authorization");

		return "1234";
	}
}
