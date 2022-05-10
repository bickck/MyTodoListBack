package com.todo.list.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.todo.list.controller.dto.QuoteDTO;
import com.todo.list.entity.base.DefaultImageEntity;
import com.todo.list.entity.base.DefaultQuoteEntity;
import com.todo.list.service.image.BackGroundImageService;
import com.todo.list.service.queto.DefaultQuetoService;

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

	@ResponseBody
	@GetMapping("/api/quotes")
	public List<DefaultQuoteEntity> responseQuotes() {
		return quoteService.getQuotes();
	}

	@ResponseBody
	@PostMapping("/api/backgrounds")
	public List<DefaultImageEntity> responseBackGrounds() {
		return null;
	}

}
