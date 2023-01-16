package com.todo.list.controller.main;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/image", headers = HttpHeaders.AUTHORIZATION)
public class ImageController {

	private final Logger logger = LoggerFactory.getLogger(getClass());


}
