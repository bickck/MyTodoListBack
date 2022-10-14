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
import com.todo.list.controller.dto.QuoteDTO;
import com.todo.list.controller.dto.auth.UserTokenDTO;
import com.todo.list.entity.UserEntity;
import com.todo.list.service.user.QuoteService;
import com.todo.list.util.auth.UserAuthToken;

/**
 * 
 * 유저의 Quote를 저장, 삭제, 수정을 제공하는 클래스
 * 
 * id는 해당 quote 아이디
 * 
 */
@RestController
@RequestMapping("/quote/manage")
public class QuoteController {

	@Autowired
	private QuoteService userQuoteService;

	@PostMapping("/save")
	public ResponseEntity<?> savetUserQuote(@RequestBody QuoteDTO quoteDTO, @UserAuthToken UserTokenDTO tokenDTO) {

		userQuoteService.quoteSave(quoteDTO, tokenDTO);

		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PostMapping("/upate/{id}")
	public ResponseEntity<?> updateUserQuote(@PathVariable Long id, @RequestBody QuoteDTO quoteDTO,
			@UserAuthToken UserTokenDTO tokenDTO) {

		userQuoteService.quoteUpdate(id, quoteDTO, tokenDTO);

		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PostMapping("/delete/{id}")
	public ResponseEntity<?> deleteUserQuote(@PathVariable Long id, @UserAuthToken UserTokenDTO tokenDTO) {

		userQuoteService.quoteDelete(id);

		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PostMapping("/heartAdd/{id}")
	public ResponseEntity<?> saveHeartUserQuote(@PathVariable Long quoteId, @UserAuthToken UserTokenDTO tokenDTO) {
		userQuoteService.saveHeartQuote(quoteId);
		return new ResponseEntity<String>(HttpStatus.OK);
	}

}
