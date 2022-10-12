package com.todo.list.service.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.todo.list.controller.dto.auth.UserTokenDTO;
import com.todo.list.entity.UserTodoEntity;
import com.todo.list.entity.base.Publish;
import com.todo.list.repository.UserTodoRepository;

@Service
public class TodoApiService {

	@Autowired
	private UserTodoRepository todoRepository;
	
	@Autowired
	private JPAQueryFactory jpaQueryFactory;

	@Transactional(readOnly = true)
	public Page<UserTodoEntity> getPublishedTodos(Pageable pageable) {
		Page<UserTodoEntity> entities = todoRepository.findTodoEntitiesByIsPublishOrderByIdDesc(Publish.PUBLISH, pageable);

		return entities;
	}

	@Transactional(readOnly = true)
	public Page<UserTodoEntity> getMostRecommandDaily(Pageable pageable) {
		Page<UserTodoEntity> entities = null;

		return entities;
	}
	
	@Transactional(readOnly = true)
	public List<UserTodoEntity> findAllPublishTodos() {
		
		return todoRepository.findAllEntitiesByIsPublish(Publish.PUBLISH);
	}
	
	@Transactional(readOnly = true)
	public List<UserTodoEntity> findMainPostByPublish(Long id) {
		//todoRepository
		return todoRepository.findAllEntitiesByIsPublish(Publish.PUBLISH);
	}

	@Transactional(readOnly = true)
	public Page<UserTodoEntity> publishTodos(int pageNumber, Pageable pageable) {
		return todoRepository.findTodoEntitiesByIsPublishOrderByIdDesc(Publish.PUBLISH, pageable);
	}

	@Transactional(readOnly = true)
	public Page<UserTodoEntity> recommandTodos(Pageable pageable) {

		Page<UserTodoEntity> pages = todoRepository.findTodoEntitiesByIsPublishOrderByIdDesc(Publish.PUBLISH, pageable);

		return pages;
	}
}
