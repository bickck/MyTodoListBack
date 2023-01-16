package com.todo.list.controller.main;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todo.list.controller.ResponseStatus;
import com.todo.list.controller.dto.QuoteDTO;
import com.todo.list.controller.dto.auth.UserTokenDTO;

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
@RequestMapping(value = "/user", headers = HttpHeaders.AUTHORIZATION)
public class QuoteController implements ResponseStatus {

	@Autowired
	private QuoteService userQuoteService;

	/**
	 * 
	 * @param quoteDTO
	 * @param tokenDTO
	 * @return
	 */

	@PostMapping(value = "/quote")
	public ResponseEntity<?> savetUserQuote(@RequestBody QuoteDTO quoteDTO, @UserAuthToken UserTokenDTO tokenDTO) {

		if (quoteDTO.getIsPublish() == null && quoteDTO.getQuote() == null && quoteDTO.getAuthor() == null) {
			return new ResponseEntity<>(ResponseStatus.FAILURE, HttpStatus.OK);
		}
		userQuoteService.saveQuote(quoteDTO, tokenDTO);
		
		return new ResponseEntity<>(ResponseStatus.SUCCESS, HttpStatus.OK);
	}

	/**
	 * 
	 * @param id
	 * @param quoteDTO
	 * @param tokenDTO
	 * @return
	 */

	@PutMapping(value = "/quote/{id}")
	public ResponseEntity<?> updateUserQuote(@PathVariable Long id, @RequestBody QuoteDTO quoteDTO,
			@UserAuthToken UserTokenDTO tokenDTO) {

		if (quoteDTO.getIsPublish() == null && quoteDTO.getQuote() == null && quoteDTO.getAuthor() == null) {
			return new ResponseEntity<>(ResponseStatus.FAILURE, HttpStatus.OK);
		}

		userQuoteService.updateQuote(id, quoteDTO, tokenDTO);

		return new ResponseEntity<>(ResponseStatus.SUCCESS, HttpStatus.OK);
	}

	/**
	 * 
	 * @param id
	 * @param tokenDTO
	 * @return
	 */

	@DeleteMapping(value = "/quote/{id}")
	public ResponseEntity<?> deleteUserQuote(@PathVariable Long id, @UserAuthToken UserTokenDTO tokenDTO) {

		userQuoteService.deleteQuote(id);

		return new ResponseEntity<>(ResponseStatus.SUCCESS, HttpStatus.OK);
	}

}
