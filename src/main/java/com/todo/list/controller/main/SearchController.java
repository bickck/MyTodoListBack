package com.todo.list.controller.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todo.list.service.SearchService;

@RestController
@RequestMapping(value = "/search")
public class SearchController {

	@Autowired
	private SearchService searchService;
	
	
	@GetMapping(value = "/keyword")
	public void requestSearchMessage(String keyword) {
		
	}
}
