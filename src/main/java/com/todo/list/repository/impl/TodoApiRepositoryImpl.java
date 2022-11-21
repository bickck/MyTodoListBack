package com.todo.list.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.todo.list.repository.custom.TodoApiCustomRepository;
import com.todo.list.repository.mapper.TodoMapper;

@Repository
@Transactional
public class TodoApiRepositoryImpl implements TodoApiCustomRepository {

	private JPAQueryFactory jpaQueryFactory;

	public TodoApiRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
		this.jpaQueryFactory = jpaQueryFactory;
	}

//	@Override
//	public Page<TodoMapper> findDailyTodosApi(Pageable pageable) {
//		// TODO Auto-generated method stub
//		return null;
//	}

//	@Override
//	public Page<TodoMapper> findRecommandTodosApi(Pageable pageable) {
//		// TODO Auto-generated method stub
//		return null;
//	}
}
