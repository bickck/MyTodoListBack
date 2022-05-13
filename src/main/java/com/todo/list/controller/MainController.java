package com.todo.list.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.todo.list.controller.dto.QuoteDTO;
import com.todo.list.entity.UserEntity;
import com.todo.list.entity.base.DefaultImageEntity;
import com.todo.list.entity.base.DefaultQuoteEntity;
import com.todo.list.service.api.UserApiService;
import com.todo.list.service.image.BackGroundImageService;
import com.todo.list.service.image.ImageUploadService;
import com.todo.list.service.queto.DefaultQuetoService;
import com.todo.list.test.TestService;

/**
 * 
 * 이 문서는 로그인 하지 않은 유저의 명언 및 배경이 없다면 디폴트 데이터가 호출될 수 있도록 하는 문서입니다..
 * 
 */

@RestController
public class MainController {

	@Autowired
	private DefaultQuetoService quoteService;

	@Autowired
	private BackGroundImageService backGroundImageService;

	@Autowired
	private ImageUploadService imageUploadService;

	@Autowired
	private UserApiService apiService;

	@ResponseBody
	@GetMapping("/api/quotes")
	public List<DefaultQuoteEntity> responseQuotes() {
		return quoteService.getQuotes();
	}

	@ResponseBody
	@GetMapping("/api/backgrounds")
	public List<DefaultImageEntity> responseBackGrounds() {
		// imageUploadService.fi
		return null;
	}

	@GetMapping("/users")
	public long getUsers(@PageableDefault(size = 10, direction = Direction.DESC) Pageable pageable) {

		Page<UserEntity> api = apiService.getUserList(pageable);
		api.getTotalElements();
		
		// Page<String> apis = api.getContent();
		
		return api.getTotalElements();
	}

	@ResponseBody
	@GetMapping("/")
	public ResponseEntity<String> main(@RequestParam(value = "test") String test, HttpServletRequest httpServletRequest)
			throws InterruptedException {

		return new ResponseEntity<String>("success", HttpStatus.OK);
	}

}
