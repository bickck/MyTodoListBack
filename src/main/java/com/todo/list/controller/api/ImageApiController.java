package com.todo.list.controller.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/image/api")
public class ImageApiController {

	/**
	 * Todo에 등록된 이미지를 가져옴
	 * 
	 * @param user unique id
	 * @return Todo Image Path and Image Name
	 */

	@GetMapping("/todo/{id}")
	public void requestTodoImage(@PathVariable Long id) {

	}

	/**
	 * User Intro 이미지를 가져옴
	 * 
	 * @param user unique id
	 * @return User Image Path and Image Name
	 */
	@GetMapping("/user/{id}")
	public void requestUserIntroImage(@PathVariable Long id) {

	}
}
