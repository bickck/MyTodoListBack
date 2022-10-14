package com.todo.list.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.IntFunction;

import javax.servlet.http.HttpServletRequest;

import org.jboss.jandex.Main;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.todo.list.controller.builder.page.PageUserBuilder;
import com.todo.list.controller.dto.BackGroundDTO;
import com.todo.list.controller.dto.ImageInfoDTO;
import com.todo.list.controller.dto.MainDataDTO;
import com.todo.list.controller.dto.QuoteDTO;
import com.todo.list.controller.dto.QuoteInfoDTO;
import com.todo.list.controller.dto.page.PageUserDTO;
import com.todo.list.entity.UserEntity;
import com.todo.list.entity.base.AdminImageEntity;
import com.todo.list.entity.base.AdminQuoteEntity;
import com.todo.list.repository.mapper.ImageInfoMapper;
import com.todo.list.service.api.UserApiService;
import com.todo.list.service.image.MainImageUploadService;
import com.todo.list.service.image.ImageService;
import com.todo.list.service.image.admin.MainBackGroundImageService;
import com.todo.list.service.queto.DefaultQuetoService;
import com.todo.list.test.service.TestService;

/**
 * 
 * 이 문서는 로그인 하지 않은 유저의 명언 및 배경이 없다면 디폴트 데이터가 호출될 수 있도록 하는 문서입니다..
 * 
 */

@RestController
public class MainController {

	private DefaultQuetoService quoteService;
	private MainBackGroundImageService backGroundImageService;
	private UserApiService apiService;

	@Autowired
	public MainController(DefaultQuetoService quoteService, MainBackGroundImageService backGroundImageService,
			UserApiService apiService) {
		this.quoteService = quoteService;
		this.backGroundImageService = backGroundImageService;
		this.apiService = apiService;
	}

	@ResponseBody
	@PostMapping("/manage/quote/1")
	public String requestQuoteSave(@RequestBody QuoteDTO quoteDTO) {
		quoteService.saveQuote(quoteDTO);

		return "success";
	}

	@ResponseBody
	@PostMapping("/manage/quote/2")
	public String requestQuoteUpdate(@RequestBody QuoteDTO quoteDTO) {
		quoteService.updateQuote(quoteDTO);

		return "success";
	}

	@ResponseBody
	@PostMapping("/manage/quote/3/{id}")
	public ResponseEntity<String> requestQuoteDelete(@PathVariable Long id) {
		quoteService.deleteQuoteById(id);

		return new ResponseEntity<String>("success", HttpStatus.OK);
	}

	@ResponseBody
	@PostMapping("/manage/image/3/")
	public ResponseEntity<String> requestImageDelete(@RequestBody BackGroundDTO backGroundDTO) {
		backGroundImageService.delete(backGroundDTO.getId(), backGroundDTO.getFilename());

		return new ResponseEntity<String>("success", HttpStatus.OK);
	}

	@ResponseBody
	@GetMapping("/api/quotes")
	public ResponseEntity<List<AdminQuoteEntity>> responseQuotes() {
		List<AdminQuoteEntity> entities = quoteService.getQuotes();
		return new ResponseEntity<List<AdminQuoteEntity>>(entities, HttpStatus.OK);
	}

	@ResponseBody
	@GetMapping("/api/infos")
	public ResponseEntity<MainDataDTO> responseMainDatas(@PageableDefault(size = 8) Pageable pageable) {

		int quoteTotalSize = quoteService.getQuotesTotalSize(pageable);
		Iterator<AdminImageEntity> adminImageEntities = backGroundImageService.imageNames().iterator();

		List<ImageInfoDTO> list = new ArrayList<ImageInfoDTO>();

		while (adminImageEntities.hasNext()) {
			list.add(new ImageInfoDTO(adminImageEntities.next()));
		}

		return new ResponseEntity<MainDataDTO>(new MainDataDTO(list, quoteTotalSize), HttpStatus.OK);
	}

	@ResponseBody
	@GetMapping("/")
	public ResponseEntity<String> main() {

		return new ResponseEntity<String>("success", HttpStatus.OK);
	}

}
