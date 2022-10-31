package com.todo.list.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.todo.list.service.SearchService;

@RestController
public class SearchController {

	@Autowired
	private SearchService searchService;
}
