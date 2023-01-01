package com.todo.list.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * 이 문서는 로그인 하지 않은 유저의 명언 및 배경이 없다면 디폴트 데이터가 호출될 수 있도록 하는 문서입니다..
 * 
 */

@RestController
public class MainController {

	@ResponseBody
	@GetMapping("/")
	public ResponseEntity<String> main() {

		return new ResponseEntity<String>("success", HttpStatus.OK);
	}

}
