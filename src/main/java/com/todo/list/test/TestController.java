package com.todo.list.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.todo.list.controller.dto.QuoteDTO;
import com.todo.list.controller.dto.UserDTO;
import com.todo.list.controller.dto.UserTokenDTO;
import com.todo.list.domain.UserEntity;
import com.todo.list.security.AuthenticationJwtToken;
import com.todo.list.util.aop.TokenValidator;

@RestController
public class TestController {

	@Autowired
	private AuthenticationJwtToken jwtLoginToken;

	// ===========================================================
	@PostMapping("/create/token")
	public String createToken() {
		String s = jwtLoginToken.createTokenTest(new UserTokenDTO((long) 1234, "username"));
		return s;

	}

	@PostMapping("/create/test")
	public void aopTest(@RequestBody UserDTO reqBody, @TokenValidator UserTokenDTO tokenUser) {
		System.out.println("Test");
		System.out.println("token User : " + tokenUser.toString());
	}

	// ===========================================================

	@PostMapping(value = "/quote/2")
	public void quoteSaveTest() {
		
		QuoteDTO dto = new QuoteDTO("quoteTest", "authorTest");
	}
}
