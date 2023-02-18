package com.todo.list.controller.main;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.todo.list.controller.ResponseStatusMessage;
import com.todo.list.controller.dto.auth.UserTokenDTO;
import com.todo.list.controller.dto.user.UserDTO;
import com.todo.list.entity.UserImageEntity;
import com.todo.list.exception.custom.ArgumentValidException;
import com.todo.list.service.user.UserService;
import com.todo.list.util.auth.UserAuthToken;
import com.todo.list.util.validation.group.Comment;

/**
 * 
 * 유저의 데이터를 저장,수정,삭제를 제공하는 클래스
 * 
 * parm : id -> user identify
 */

@Validated
@RestController
@RequestMapping(value = "/user", headers = HttpHeaders.AUTHORIZATION)
public class UserController implements ResponseStatusMessage {

	private UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	/**
	 * 
	 * @param id
	 * @param userDTO
	 * @return
	 * @throws Exception
	 */

	@PutMapping("/intro/comment")
	public ResponseEntity<?> updateUserIntroComment(@Validated(Comment.class) @RequestBody UserDTO userDTO,
			@UserAuthToken UserTokenDTO userTokenDTO, BindingResult bindingResult) throws Exception {

		if (bindingResult.hasErrors()) {
			throw new ArgumentValidException(bindingResult.getFieldError());
		}

		userService.updateUserIntroComment(userDTO.getIntroComment(), userTokenDTO);

		return new ResponseEntity<>(ResponseStatusMessage.SUCCESS, HttpStatus.OK);
	}

	/**
	 * 
	 * @param id
	 * @return
	 */

	@DeleteMapping("/account/{id}")
	public ResponseEntity<?> deleteUser(@UserAuthToken UserTokenDTO userTokenDTO) {

		userService.userDelete(userTokenDTO.getId());

		return new ResponseEntity<>(ResponseStatusMessage.SUCCESS, HttpStatus.OK);
	}

	/**
	 * 
	 * @param id
	 * @param userDTO
	 * @return
	 */

	@PutMapping("/intro")
	public ResponseEntity<?> updateUserIntro(@RequestBody UserDTO userDTO, @UserAuthToken UserTokenDTO userTokenDTO) {

		userService.userUpdate(userDTO, userTokenDTO);

		return new ResponseEntity<>(ResponseStatusMessage.SUCCESS, HttpStatus.OK);
	}

	/**
	 * 
	 * @param userDTO
	 * @param file
	 * @return
	 */

	@PutMapping(value = "/intro/image", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.ALL_VALUE })
	public ResponseEntity<?> updateUserImage(@UserAuthToken UserTokenDTO userDTO,
			@RequestParam(value = "file") MultipartFile file) {

		UserImageEntity entity = userService.updateUserIntroImage(userDTO.getId(), file);

		if (entity == null) {
			return new ResponseEntity<>(ResponseStatusMessage.FAILURE, HttpStatus.OK);
		}

		return new ResponseEntity<>(ResponseStatusMessage.SUCCESS, HttpStatus.OK);
	}

	/**
	 * 
	 * @param userDTO
	 * @return
	 */

	@DeleteMapping(value = "/intro/image")
	public ResponseEntity<?> deleteUserImage(@UserAuthToken UserTokenDTO userDTO) {

		boolean result;

		try {
			result = userService.deleteUserIntroImage(userDTO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<>(ResponseStatusMessage.FAILURE, HttpStatus.OK);
		}

		if (!result) {
			return new ResponseEntity<>(ResponseStatusMessage.FAILURE, HttpStatus.OK);

		}

		return new ResponseEntity<>(ResponseStatusMessage.SUCCESS, HttpStatus.OK);
	}

}
