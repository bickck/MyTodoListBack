package com.todo.list.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todo.list.controller.dto.auth.UserTokenDTO;
import com.todo.list.service.user.HeartService;
import com.todo.list.util.auth.UserAuthToken;

/**
 * 
 * @author 3d193
 *
 */

@RestController
@RequestMapping(value = "/heart", consumes = { MediaType.APPLICATION_JSON_VALUE })
public class HeartController {

	@Autowired
	private HeartService heartService;

	/**
	 * 
	 * @param id
	 * @return
	 */

	@RequestMapping(value = "/todo/{id}")
	public ResponseEntity<?> requestSaveTodoHeart(@PathVariable Long id, @UserAuthToken UserTokenDTO userTokenDTO) {

		heartService.saveTodoHeart(id, userTokenDTO);

		return new ResponseEntity<>(HttpStatus.OK);
	}

	/**
	 * 
	 * @param id
	 * @return
	 */

	@RequestMapping(value = "/quote/{id}")
	public ResponseEntity<?> requestSaveQuoteHeart(@PathVariable Long id, @UserAuthToken UserTokenDTO userTokenDTO) {

		heartService.saveQuoteHeart(id, userTokenDTO);

		return new ResponseEntity<>(HttpStatus.OK);
	}

	/**
	 * 
	 * @param id
	 * @return
	 */

	@RequestMapping(value = "/cancle/todo/{id}")
	public ResponseEntity<?> requestCancleTodoHeart(@PathVariable Long id, @UserAuthToken UserTokenDTO userTokenDTO) {

		heartService.cancleTodoHeart(id, userTokenDTO);

		return new ResponseEntity<>(HttpStatus.OK);
	}

	/**
	 * 
	 * @param id
	 * @return
	 */

	@RequestMapping(value = "/cancle/todo/{id}")
	public ResponseEntity<?> requestCancleQuoteHeart(@PathVariable Long id, @UserAuthToken UserTokenDTO userTokenDTO) {

		heartService.cancleQuoteHeart(id, userTokenDTO);

		return new ResponseEntity<>(HttpStatus.OK);
	}
}
