package com.todo.list.repository.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.todo.list.repository.quote.QuoteApiRepository;

@Repository
@Transactional
public class QuoteApiRepositoryImpl implements QuoteApiRepository {

	// querydsl
	private JPAQueryFactory jpaQueryFactory;

	public QuoteApiRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
		// TODO Auto-generated constructor stub
		this.jpaQueryFactory = jpaQueryFactory;
	}

}
