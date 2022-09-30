package com.todo.list.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todo.list.controller.builder.QuoteBuilder;
import com.todo.list.controller.dto.auth.UserTokenDTO;
import com.todo.list.controller.dto.service.QuoteDTO;
import com.todo.list.entity.UserEntity;
import com.todo.list.service.user.UserQuoteService;
import com.todo.list.util.auth.UserAuthToken;

/**
 * 
 * 해당 유저의 Quote 데이터를 가지고 있는 클래스
 * 
 * id는 해당
 * 
 */
@RestController
@RequestMapping("/quote/manage")
public class QuoteController {

	@Autowired
	private UserQuoteService userQuoteService;

	@PostMapping("/save")
	public ResponseEntity<?> savetUserQuote(@RequestBody QuoteDTO quoteDTO, @UserAuthToken UserTokenDTO tokenDTO) {

		userQuoteService.quoteSave(quoteDTO, tokenDTO);

		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PostMapping("/upate/{id}")
	public ResponseEntity<?> updateUserQuote(@PathVariable Long id, @RequestBody QuoteDTO quoteDTO,
			@UserAuthToken UserTokenDTO tokenDTO) {

		userQuoteService.quoteUpdate(id, quoteDTO,tokenDTO);

		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PostMapping("/delete/{id}")
	public ResponseEntity<?> deleteUserQuote(@PathVariable Long id, @RequestBody QuoteDTO quoteDTO,
			@UserAuthToken UserTokenDTO tokenDTO) {

		userQuoteService.quoteDelete(id);

		return new ResponseEntity<>(HttpStatus.OK);
	}

}
