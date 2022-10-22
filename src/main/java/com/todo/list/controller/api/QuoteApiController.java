package com.todo.list.controller.api;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.todo.list.controller.builder.page.PageQuoteBuilder;
import com.todo.list.controller.dto.QuoteDTO;
import com.todo.list.controller.dto.auth.UserTokenDTO;
import com.todo.list.controller.dto.page.PageQuoteDTO;
import com.todo.list.entity.QuoteEntity;
import com.todo.list.repository.mapper.QuoteMapper;
import com.todo.list.service.api.QuoteApiService;
import com.todo.list.service.api.UserApiService;
import com.todo.list.service.user.UserService;
import com.todo.list.util.auth.UserAuthToken;

/**
 * 로그인 필요 없이 Quote API를 제공하는 클래스
 * 
 */

@RestController
@RequestMapping("/quote/api")
public class QuoteApiController {

	private UserApiService userApiService;
	private QuoteApiService quoteApiService;

	@Autowired
	public QuoteApiController(UserApiService userApiService, QuoteApiService quoteApiService) {
		this.userApiService = userApiService;
		this.quoteApiService = quoteApiService;
	}

	/**
	 * Quote의 자세한 정보를 가져옴
	 * 
	 * @param Quote Unique Id
	 * @return Quote Detail
	 */

	@GetMapping("/{id}")
	@ResponseBody
	public ResponseEntity<QuoteDTO> requestQuoteDetailById(@PathVariable Long id) {

		QuoteDTO quoteDTOs = new QuoteDTO(quoteApiService.requestQuoteApiById(id));

		return new ResponseEntity<QuoteDTO>(quoteDTOs, HttpStatus.OK);
	}

	/**
	 * Publish된 모든 Quote를 가져옴
	 * 
	 * @param pageable
	 * @return All Quote
	 */

	@GetMapping("/mainQuote")
	@ResponseBody
	public ResponseEntity<List<QuoteDTO>> requestQuotesApi(@PageableDefault(size = 10, page = 0) Pageable pageable) {

		return new ResponseEntity<List<QuoteDTO>>(HttpStatus.OK);
	}

	/**
	 * HEART가 가장 많은 Quote List를 가져옴
	 * 
	 * @param pageable
	 * @return Recommand All Quote
	 */

	@GetMapping("/recommand/quotes")
	@ResponseBody
	public ResponseEntity<List<QuoteDTO>> requestRecommandQuotes(@PageableDefault(size = 10, page = 0) Pageable pageable) {

		return new ResponseEntity<List<QuoteDTO>>(HttpStatus.OK);
	}

	/**
	 * 해당 날짜에 HEART를 가장 많이 받은 Quote List를 가져옴
	 * 
	 * @param pageable
	 * @return Recommand Daily All Quote
	 */

	@GetMapping("/daily/quotes")
	@ResponseBody
	public ResponseEntity<List<QuoteDTO>> requestDailyQuotes(@PageableDefault(size = 10, page = 0) Pageable pageable) {

		return new ResponseEntity<List<QuoteDTO>>(HttpStatus.OK);
	}

}
