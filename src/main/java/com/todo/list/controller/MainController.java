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
import com.todo.list.domain.ImageEntity;
import com.todo.list.domain.QuetoEntity;
import com.todo.list.service.image.BackGroundImageService;
import com.todo.list.service.queto.QuetoService;

@RestController
public class MainController {

	@Autowired
	private QuetoService quoteService;

	@Autowired
	private BackGroundImageService backGroundImageService;

	@ResponseBody
	@GetMapping("/api/quotes")
	public List<QuetoEntity> responseQuotes() {
		return quoteService.getQuotes();
	}

	@ResponseBody
	@PostMapping("/api/backgrounds")
	public List<ImageEntity> responseBackGrounds() {
		return null;
	}

}
