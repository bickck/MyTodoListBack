package com.todo.list.test.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todo.list.controller.ResponseStatusMessage;
import com.todo.list.controller.dto.QuoteDTO;
import com.todo.list.controller.dto.TodoDTO;
import com.todo.list.util.validation.group.QuoteAccessArgumentGroup;

@RestController
@RequestMapping(value = "/bind/test")
public class ArgumentBindTestController {

	@GetMapping(value = "/quote")
	public ResponseEntity<?> bindQuoteArgumentTest(
			@Validated(QuoteAccessArgumentGroup.class) @RequestBody QuoteDTO quoteDTO, BindingResult bindingResult) {

		if(bindingResult.hasFieldErrors()) {
			throw new NullPointerException();
		}
		
		System.out.println(quoteDTO.getAuthor());

		System.out.println(quoteDTO.getQuote());

		System.out.println(quoteDTO.getIsPublish());

		return new ResponseEntity<>(ResponseStatusMessage.SUCCESS, HttpStatus.OK);
	}

	@GetMapping(value = "/todo")
	public ResponseEntity<?> bindArgumentIsPublishTest(@RequestBody TodoDTO quoteDTO) {

		System.out.println(quoteDTO.getIsPublish());

		return new ResponseEntity<>(ResponseStatusMessage.SUCCESS, HttpStatus.OK);
	}
}
