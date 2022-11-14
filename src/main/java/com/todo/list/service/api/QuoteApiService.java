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
import com.todo.list.repository.quote.QuoteRepository;

@Service
public class QuoteApiService {

	private QuoteRepository quoteRepository;
	private JPAQueryFactory jpaQueryFactory;

	@Autowired
	public QuoteApiService(QuoteRepository quoteRepository, JPAQueryFactory jpaQueryFactory) {
		this.quoteRepository = quoteRepository;
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

		return quoteRepository.findRecommandQuotes(pageable, Publish.PUBLISH);
	}

	/**
	 * 
	 * @param pageable
	 * @return
	 */

	@Transactional(readOnly = true)
	public Page<QuoteMapper> findDaliyQuotes(Pageable pageable) {

		return quoteRepository.findDailyQuotes(pageable, Publish.PUBLISH);
	}

//	@Transactional(readOnly = true)
//	public QuoteMapper testQuqery() {
//
//		//jpaQueryFactory.select();
//		
//		return quoteRepository.findQuery();
//	}

}
