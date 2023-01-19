package com.todo.list.test.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todo.list.controller.ResponseStatus;
import com.todo.list.controller.dto.user.UserDTO;
import com.todo.list.exception.custom.ArgumentValidException;
import com.todo.list.util.validation.group.Comment;
import com.todo.list.util.validation.group.LoginAccessArgumentGroup;
import com.todo.list.util.validation.group.RegisterAccessArgumentGroup;

@RestController
@RequestMapping(value = "/valid/test")
public class ValidTestController {

	@Validated(Comment.class)
	@GetMapping("/comment")
	public ResponseEntity<?> updateUserIntroComment(@Valid @RequestBody UserDTO userDTO, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return new ResponseEntity<>(HttpStatus.OK);
		}

		return new ResponseEntity<>(ResponseStatus.SUCCESS, HttpStatus.OK);
	}

	@GetMapping("/createUser")
	public ResponseEntity<?> loginUserValidated(@Validated(LoginAccessArgumentGroup.class) @RequestBody UserDTO userDTO,
			BindingResult bindingResult) throws Exception {

		if (bindingResult.hasErrors()) {
			throw new ArgumentValidException(bindingResult.getFieldError());
		}

		return new ResponseEntity<>(ResponseStatus.SUCCESS, HttpStatus.OK);
	}

	@GetMapping("/registerUser")
	public ResponseEntity<?> registerUserValidated(
			@Validated(RegisterAccessArgumentGroup.class) @RequestBody UserDTO userDTO, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.OK);
		}

		return new ResponseEntity<>(ResponseStatus.SUCCESS, HttpStatus.OK);
	}

}
