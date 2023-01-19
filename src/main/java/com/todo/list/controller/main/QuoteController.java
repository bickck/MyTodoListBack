package com.todo.list.controller.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
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
import com.todo.list.controller.response.message.ResponseMessageEntity;
import com.todo.list.exception.custom.ArgumentValidException;
import com.todo.list.service.user.QuoteService;
import com.todo.list.util.auth.UserAuthToken;
import com.todo.list.util.validation.group.QuoteAccessArgumentGroup;

/**
 * 
 * 유저의 Quote를 저장, 삭제, 수정을 제공하는 클래스
 * 
 * id는 해당 quote 아이디
 * 
 */
@RestController
@RequestMapping(value = "/user/quote", headers = HttpHeaders.AUTHORIZATION)
public class QuoteController implements ResponseStatus {

	@Autowired
	private QuoteService userQuoteService;

	/**
	 * 
	 * @param quoteDTO
	 * @param tokenDTO
	 * @return
	 * @throws Exception
	 */

	@PostMapping(value = "/post")
	public ResponseMessageEntity<?> savetUserQuote(@UserAuthToken UserTokenDTO tokenDTO,
			@Validated(value = QuoteAccessArgumentGroup.class) @RequestBody QuoteDTO quoteDTO,
			BindingResult bindingResult) throws Exception {

		if (bindingResult.hasErrors()) {
			throw new ArgumentValidException(bindingResult.getFieldError());
		}

		userQuoteService.saveQuote(quoteDTO, tokenDTO);

		return new ResponseMessageEntity<String>(ResponseStatus.SUCCESS, HttpStatus.OK);
	}

	/**
	 * 
	 * @param id
	 * @param quoteDTO
	 * @param tokenDTO
	 * @return
	 */

	@PutMapping(value = "/post/{id}")
	public ResponseMessageEntity<?> updateUserQuote(@PathVariable Long id, @UserAuthToken UserTokenDTO tokenDTO,
			@Validated(value = QuoteAccessArgumentGroup.class) @RequestBody QuoteDTO quoteDTO,
			BindingResult bindingResult) throws Exception {

		if (bindingResult.hasErrors()) {
			throw new ArgumentValidException(bindingResult.getFieldError());
		}

		userQuoteService.updateQuote(id, quoteDTO, tokenDTO);

		return new ResponseMessageEntity<String>(ResponseStatus.SUCCESS, HttpStatus.OK);
	}

	/**
	 * 
	 * @param id
	 * @param tokenDTO
	 * @return
	 */

	@DeleteMapping(value = "/post/{id}")
	public ResponseEntity<?> deleteUserQuote(@PathVariable Long id, @UserAuthToken UserTokenDTO tokenDTO) {

		userQuoteService.deleteQuote(id);

		return new ResponseEntity<>(ResponseStatus.SUCCESS, HttpStatus.OK);
	}

}
