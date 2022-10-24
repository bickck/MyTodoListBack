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
import com.todo.list.repository.QuoteRepository;
import com.todo.list.repository.mapper.QuoteMapper;

@Service
public class QuoteApiService {

	@Autowired
	private QuoteRepository quoteRepository;

	@Autowired
	private JPAQueryFactory jpaQueryFactory;

	@Transactional(readOnly = true)
	public Page<QuoteMapper> mainQuoteLists(Pageable pageable) {

		return quoteRepository.findMainQuotes(pageable, Publish.PUBLISH);
	}

	@Transactional(readOnly = true)
	public QuoteEntity requestQuoteApiById(Long id) {

		return quoteRepository.findById(id).get();
	}

//	@Transactional(readOnly = true)
//	public QuoteMapper testQuqery() {
//
//		//jpaQueryFactory.select();
//		
//		return quoteRepository.findQuery();
//	}

}
