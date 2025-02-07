package com.todo.list.controller.api;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.todo.list.controller.dto.QuoteDTO;
import com.todo.list.repository.mapper.QuoteMapper;
import com.todo.list.service.api.QuoteApiService;
import com.todo.list.service.api.UserApiService;

/**
 * 로그인 필요 없이 Quote API를 제공하는 클래스
 * 
 * PUBLISH가 되어 있는 Quote 리스트
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
	public ResponseEntity<?> requestQuoteDetailById(@PathVariable Long id) {

		QuoteDTO quoteDTOs = new QuoteDTO(quoteApiService.requestQuoteApiById(id));

		return new ResponseEntity<QuoteDTO>(quoteDTOs, HttpStatus.OK);
	}

	/**
	 * Publish된 모든 Quote를 가져옴
	 * 
	 * @param pageable
	 * @return All Quote
	 */

	@GetMapping("/mainquote")
	@ResponseBody
	public ResponseEntity<?> requestQuotesApi(@PageableDefault(size = 3, page = 0) Pageable pageable) {

		Page<QuoteMapper> page = quoteApiService.mainQuotes(pageable);

		return new ResponseEntity<Page<QuoteMapper>>(page, HttpStatus.OK);
	}

	/**
	 * HEART가 가장 많은 Quote List를 가져옴
	 * 
	 * @param pageable
	 * @return Recommand All Quote
	 */

	@GetMapping("/recommand")
	@ResponseBody
	public ResponseEntity<?> requestRecommandQuotes(@PageableDefault(size = 3, page = 0) Pageable pageable) {

		Page<QuoteMapper> page = quoteApiService.findRecommandQuotes(pageable);

		return new ResponseEntity<Page<QuoteMapper>>(page, HttpStatus.OK);
	}

	/**
	 * 해당 날짜에 HEART를 가장 많이 받은 Quote List를 가져옴
	 * 
	 * @param pageable
	 * @return Recommand Daily All Quote
	 */

	@GetMapping("/daily")
	@ResponseBody
	public ResponseEntity<?> requestDailyQuotes(@PageableDefault(size = 3, page = 0) Pageable pageable) {

		Page<QuoteMapper> page = quoteApiService.findDaliyQuotes(pageable);

		return new ResponseEntity<Page<QuoteMapper>>(page, HttpStatus.OK);
	}
	
	/**
	 * 
	 * 유저의 Quote 모든 정보 가져오기
	 */

	@GetMapping("/{username}/quotes")
	public ResponseEntity<?> getUserApiQuotes(
			@PathVariable String username,
			@PageableDefault(size = 5, direction = Direction.ASC) Pageable pageable) {

		Page<QuoteMapper> page = quoteApiService.getUserQuotesByUsername(username, pageable);

		return new ResponseEntity<Page<QuoteMapper>>(page, HttpStatus.OK);
	}
	
	
	/**
	 * 
	 */

	@GetMapping(value = "/like/{username}")
	public ResponseEntity<?> requestUserLikeQuotesByUsername(
			@PathVariable String username,
			@PageableDefault(size = 5, direction = Direction.ASC) Pageable pageable) {

		Page<QuoteMapper> page = null;
				//quoteApiService.getUserQuotesByUsername(userTokenDTO, pageable);

		return new ResponseEntity<Page<QuoteMapper>>(page, HttpStatus.OK);
	}
	
}
