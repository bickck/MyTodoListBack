package com.todo.list.controller.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.todo.list.controller.builder.QuoteBuilder;
import com.todo.list.controller.dto.auth.UserTokenDTO;
import com.todo.list.controller.dto.service.QuoteDTO;
import com.todo.list.entity.UserEntity;
import com.todo.list.util.auth.UserAuthToken;

@RestController
public class QuoteController {

	/**
	 * Quote CRUD
	 * 
	 *  id는 해당 게시물의 고유 번호 
	 */

	// save = 1, update = 2, delete = 3
	@PostMapping("/quote/1")
	public ResponseEntity<?> savetUserQuote(@RequestBody QuoteDTO quoteDTO, @UserAuthToken UserTokenDTO tokenDTO) {

//		UserEntity user = userApiService.getUserApi(tokenDTO);
//		QuoteBuilder builder = new QuoteBuilder();
//		builder.setQuote(quoteDTO.getQuote());
//		builder.setAuthor(quoteDTO.getAuthor());
//
//		userQuoteService.quoteInsert(builder.builder(), user);

		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PostMapping("/quote/2/{id}")
	public ResponseEntity<?> updateUserQuote(@PathVariable Long id, @UserAuthToken UserTokenDTO tokenDTO) {

//		userQuoteService.quoteDelete(id);

		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@PostMapping("/quote/3/{id}")
	public ResponseEntity<?> deleteUserQuote(@PathVariable Long id, @UserAuthToken UserTokenDTO tokenDTO) {

//		userQuoteService.quoteDelete(id);

		return new ResponseEntity<>(HttpStatus.CREATED);
	}

}
