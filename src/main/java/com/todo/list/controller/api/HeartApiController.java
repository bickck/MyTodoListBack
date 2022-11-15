package com.todo.list.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todo.list.service.api.HeartApiService;

@RestController
@RequestMapping(value = "/heart/api")
public class HeartApiController {

	@Autowired
	private HeartApiService heartApiService;
	
	
}
