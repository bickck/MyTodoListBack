package com.todo.list.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.todo.list.repository.custom.QuoteApiCustomRepository;
import com.todo.list.repository.mapper.QuoteMapper;

@Repository
@Transactional
public class QuoteApiCustomRepositoryImpl implements QuoteApiCustomRepository {


	@Autowired
	private EntityManager entityManager;
	
	private JPAQueryFactory jpaQueryFactory;

	@Autowired
	public QuoteApiCustomRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
		// TODO Auto-generated constructor stub
		this.jpaQueryFactory = jpaQueryFactory;
	}

//	@Override
//	public Page<QuoteMapper> dailyQuotesApi(Pageable pageable) {
//		// TODO Auto-generated method stub
//		return null;
//	}

//	@Override
//	public Page<QuoteMapper> recommandQuotesApi(Pageable pageable) {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
