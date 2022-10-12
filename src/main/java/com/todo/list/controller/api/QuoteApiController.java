package com.todo.list.controller.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todo.list.controller.builder.page.PageQuoteBuilder;
import com.todo.list.controller.dto.auth.UserTokenDTO;
import com.todo.list.controller.dto.page.PageQuoteDTO;
import com.todo.list.controller.dto.service.QuoteDTO;
import com.todo.list.entity.UserQuoteEntity;
import com.todo.list.service.api.UserApiService;
import com.todo.list.service.user.UserService;
import com.todo.list.util.auth.UserAuthToken;

@RestController
@RequestMapping("/quote/api")
public class QuoteApiController {

	private UserApiService userApiService;

	@Autowired
	public QuoteApiController(UserApiService userApiService) {

		this.userApiService = userApiService;

	}

	/**
	 * 
	 * 유저의 Quote 모든 정보 가져오기
	 */

//	@PostMapping("/quotes")
//	public ResponseEntity<PageQuoteDTO> getUserApiQuotes(
//			@PageableDefault(size = 8, direction = Direction.ASC) Pageable pageable) {
//		UserTokenDTO userTokenDTO = new UserTokenDTO((long) 1, "username0");
//
//		Page<QuoteEntity> entities = userApiService.getUserquotes(userTokenDTO, pageable);
//		PageQuoteBuilder builder = new PageQuoteBuilder();
//		builder.setLists(entities.getContent());
//		builder.setNumber(entities.getNumber());
//		builder.setNumberOfElements(entities.getNumberOfElements());
//		builder.setPageable(entities.getPageable());
//		builder.setSize(entities.getSize());
//		builder.setTotalPages(entities.getTotalPages());
//		builder.setTotalElements(entities.getTotalElements());
//		return new ResponseEntity<PageQuoteDTO>(builder.builder(), HttpStatus.OK);
//	}
//	
	@PostMapping("/{id}")
	public ResponseEntity<List<QuoteDTO>> getUserApiQuotesByid(@PathVariable Integer id) {
		PageRequest pageRequest = PageRequest.of(id, 10, Sort.Direction.ASC, "id");

//		Iterator<UserQuoteEntity> itr = userApiService.getUserquotes(userTokenDTO, pageRequest).listIterator();
		List<QuoteDTO> list = new ArrayList<QuoteDTO>();
//		while (itr.hasNext()) {
//			QuoteBuilder builder = new QuoteBuilder();
//			builder.setId(itr.next().getId());
//			builder.setAuthor(itr.next().getAuthor());
//			builder.setQuote(itr.next().getQueto());
//			builder.setUser(itr.next().getUser());
//			list.add(builder.builder());
//		}
		return new ResponseEntity<List<QuoteDTO>>(list, HttpStatus.OK);
	}

//	@PostMapping("/recommand/all")
//	public ResponseEntity<List<QuoteDTO>> getUserApiQuotesByid(@PathVariable Integer id) {
//
//		return new ResponseEntity<List<QuoteDTO>>(list, HttpStatus.OK);
//	}
	
//	@PostMapping("/recommand/{id}")
//	public ResponseEntity<List<QuoteDTO>> getUserApiQuotesByid(@PathVariable Integer id) {
//
//		return new ResponseEntity<List<QuoteDTO>>(list, HttpStatus.OK);
//	}
	
}
