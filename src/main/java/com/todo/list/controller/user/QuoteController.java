package com.todo.list.controller.user;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.todo.list.controller.ResponseStatus;
import com.todo.list.controller.builder.QuoteBuilder;
import com.todo.list.controller.dto.QuoteDTO;
import com.todo.list.controller.dto.auth.UserTokenDTO;
import com.todo.list.entity.TodoEntity;
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
@RequestMapping("/user/quote/manage")
public class QuoteController implements ResponseStatus {

	@Autowired
	private QuoteService userQuoteService;

	/**
	 * 
	 * @param quoteDTO
	 * @param tokenDTO
	 * @return
	 */

	@ResponseBody
	@PostMapping("/save")
	public ResponseEntity<?> savetUserQuote(@RequestBody QuoteDTO quoteDTO, @UserAuthToken UserTokenDTO tokenDTO) {

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

	@ResponseBody
	@PostMapping("/update/{id}")
	public ResponseEntity<?> updateUserQuote(@PathVariable Long id, @RequestBody QuoteDTO quoteDTO,
			@UserAuthToken UserTokenDTO tokenDTO) {

		userQuoteService.updateQuote(id, quoteDTO, tokenDTO);

		return new ResponseEntity<>(ResponseStatus.SUCCESS, HttpStatus.OK);
	}

	/**
	 * 
	 * @param id
	 * @param tokenDTO
	 * @return
	 */

	@ResponseBody
	@PostMapping("/delete/{id}")
	public ResponseEntity<?> deleteUserQuote(@PathVariable Long id, @UserAuthToken UserTokenDTO tokenDTO) {

		userQuoteService.deleteQuote(id);

		return new ResponseEntity<>(ResponseStatus.SUCCESS, HttpStatus.OK);
	}

	/**
	 * 
	 * @param quoteId
	 * @param tokenDTO
	 * @return
	 */

	@ResponseBody
	@PostMapping("/heart/add/{id}")
	public ResponseEntity<?> saveHeartQuote(@PathVariable Long id, @UserAuthToken UserTokenDTO tokenDTO) {
		userQuoteService.saveQuoteHeart(id);
		return new ResponseEntity<>(ResponseStatus.SUCCESS, HttpStatus.OK);
	}

	/**
	 * 
	 * @param quoteId
	 * @param tokenDTO
	 * @return
	 */

	@ResponseBody
	@PostMapping("/heart/update/{id}")
	public ResponseEntity<?> updateHeartQuote(@PathVariable Long id, @UserAuthToken UserTokenDTO tokenDTO) {
		userQuoteService.saveQuoteHeart(id);
		return new ResponseEntity<>(ResponseStatus.SUCCESS, HttpStatus.OK);
	}

}
