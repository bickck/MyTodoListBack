package com.todo.list.controller.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * @author 3d193
 *
 */

@RestController
@RequestMapping(value = "/user/like")
public class UserLikeApiController {

	/**
	 * 
	 */

	@PostMapping(value = "/todo")
	public void requestUserLikeTodoLists() {

	}

	/**
	 * 
	 */

	@PostMapping(value = "/quote")
	public void requestUserLikeQuoteLists() {

	}

}
