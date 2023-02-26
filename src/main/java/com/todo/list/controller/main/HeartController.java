package com.todo.list.controller.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todo.list.controller.ResponseStatusMessage;
import com.todo.list.controller.dto.auth.UserTokenDTO;
import com.todo.list.message.MessageSender;
import com.todo.list.service.main.HeartService;
import com.todo.list.util.auth.UserAuthToken;

/**
 * 
 * @author 3d1935
 *
 */

@RestController
@RequestMapping(value = "/heart", consumes = { MediaType.APPLICATION_JSON_VALUE }, headers = HttpHeaders.AUTHORIZATION)
public class HeartController {

	@Autowired
	private HeartService heartService;

	@Autowired
	private MessageSender messageSender;

	/**
	 * 
	 * @param todo id
	 * @return
	 */

	@PostMapping(value = "/todo/{id}")
	public ResponseEntity<?> requestSaveTodoHeart(@PathVariable Long id, @UserAuthToken UserTokenDTO userTokenDTO) {

		String uuid = heartService.saveTodoHeart(id, userTokenDTO);

		messageSender.sendTodoHeartEventMessage(id, userTokenDTO.getUsername() + "님이 좋아요를 눌르셨습니다.");

		return new ResponseEntity<String>(uuid, HttpStatus.OK);
	}

	/**
	 * 
	 * @param quote id
	 * @return
	 */

	@PostMapping(value = "/quote/{id}")
	public ResponseEntity<?> requestSaveQuoteHeart(@PathVariable Long id, @UserAuthToken UserTokenDTO userTokenDTO) {

		heartService.saveQuoteHeart(id, userTokenDTO);

		messageSender.sendQuoteHeartEventMessage(id, userTokenDTO.getUsername() + "님이 좋아요를 눌르셨습니다.");

		return new ResponseEntity<>(ResponseStatusMessage.SUCCESS, HttpStatus.OK);
	}

	/**
	 * 
	 * @param id
	 * @return
	 */

	@DeleteMapping(value = "/todo/{uuid}")
	public ResponseEntity<?> requestCancleTodoHeart(@PathVariable String uuid,
			@UserAuthToken UserTokenDTO userTokenDTO) {

		heartService.cancleTodoHeart(uuid, userTokenDTO);

		return new ResponseEntity<>(ResponseStatusMessage.SUCCESS, HttpStatus.OK);
	}

	/**
	 * 
	 * @param id
	 * @return
	 */

	@DeleteMapping(value = "/quote/{uuid}")
	public ResponseEntity<?> requestCancleQuoteHeart(@PathVariable String uuid,
			@UserAuthToken UserTokenDTO userTokenDTO) {

		heartService.cancleQuoteHeart(uuid, userTokenDTO);

		return new ResponseEntity<>(ResponseStatusMessage.SUCCESS, HttpStatus.OK);
	}
}
