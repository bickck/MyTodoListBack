package com.todo.list.service.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.todo.list.entity.QuoteEntity;
import com.todo.list.entity.base.Publish;
import com.todo.list.repository.mapper.QuoteMapper;
import com.todo.list.repository.quote.QuoteLikeRepository;
import com.todo.list.repository.quote.QuoteRepository;

@Service
public class QuoteApiService {

	private QuoteRepository quoteRepository;
	private QuoteLikeRepository quoteLikeRepository;
	private JPAQueryFactory jpaQueryFactory;

	@Autowired
	public QuoteApiService(QuoteRepository quoteRepository, QuoteLikeRepository quoteLikeRepository,
			JPAQueryFactory jpaQueryFactory) {
		this.quoteRepository = quoteRepository;
		this.quoteLikeRepository = quoteLikeRepository;
		this.jpaQueryFactory = jpaQueryFactory;
	}

	/**
	 * 
	 * @param pageable
	 * @return
	 */

	@Transactional(readOnly = true)
	public Page<QuoteMapper> mainQuotes(Pageable pageable) {

		return quoteRepository.findMainQuotes(pageable, Publish.PUBLISH);
	}

	/**
	 * 
	 * @param id
	 * @return
	 */

	@Transactional(readOnly = true)
	public QuoteEntity requestQuoteApiById(Long id) {

		return quoteRepository.findById(id).get();
	}

	/**
	 * 
	 * @param pageable
	 * @return
	 */

	@Transactional(readOnly = true)
	public Page<QuoteMapper> findRecommandQuotes(Pageable pageable) {

//		quoteRepository.findRecommandQuotesApi(pageable);

		return quoteRepository.findRecommandQuotes(pageable, Publish.PUBLISH);
	}

	/**
	 * 
	 * @param pageable
	 * @return
	 */

	@Transactional(readOnly = true)
	public Page<QuoteMapper> findDaliyQuotes(Pageable pageable) {

//		quoteRepository.findDailyQuotesApi(pageable);

		return quoteRepository.findDailyQuotes(pageable, Publish.PUBLISH);
	}

	/**
	 * 
	 * @param userDTO
	 * @param pageable
	 * @return User Quote List
	 */

	@Transactional(readOnly = true)
	public Page<QuoteMapper> getUserQuotesByUsername(String username, Pageable pageable) {

		Page<QuoteMapper> page = quoteRepository.findUserQuoteByUsername(username, pageable);

		return page;
	}

	/**
	 * 
	 * @param userDTO
	 * @param pageable
	 * @return User Quote List
	 */

	@Transactional(readOnly = true)
	public Page<QuoteMapper> getUserLikeQuotesByUsername(String username, Pageable pageable) {

		Page<QuoteMapper> page = null;
				//quoteRepository.findUserQuoteByUsername(username, pageable);

		return page;
	}

//	@Transactional(readOnly = true)
//	public QuoteMapper testQuqery() {
//
//		//jpaQueryFactory.select();
//		
//		return quoteRepository.findQuery();
//	}

}
