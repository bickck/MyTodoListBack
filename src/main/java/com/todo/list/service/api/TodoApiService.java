package com.todo.list.service.api;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.todo.list.controller.dto.TodoDTO;
import com.todo.list.controller.dto.auth.UserTokenDTO;
import com.todo.list.entity.TodoEntity;
import com.todo.list.entity.TodoImageEntity;
import com.todo.list.entity.UserEntity;
import com.todo.list.entity.base.Publish;
import com.todo.list.repository.TodoRepository;
import com.todo.list.repository.mapper.TodoMapper;

@Service
public class TodoApiService {

	@Autowired
	private TodoRepository todoRepository;

	@Autowired
	private JPAQueryFactory jpaQueryFactory;

	private EntityManager entitiyManager;
	private CriteriaBuilder criteriaBuilder;

	@Autowired
	public TodoApiService(EntityManager entityManager) {
		criteriaBuilder = entityManager.getCriteriaBuilder();
		this.entitiyManager = entityManager;
	}

	/**
	 * 
	 * @param id
	 * @return
	 */

	@Transactional(readOnly = true)
	public TodoEntity findPostDetailById(Long id) {

		return todoRepository.findTodoEntityById(id);
	}

	/**
	 * 
	 * @param pageable
	 * @return
	 */

	@Transactional(readOnly = true)
	public Page<TodoMapper> findMainTodos(Pageable pageable) {

		return todoRepository.findTodoMainPostByPublish(Publish.PUBLISH, pageable);
	}

	/**
	 * 
	 * @param pageable
	 * @return
	 */

	@Transactional(readOnly = true)
	public Page<TodoMapper> findRecommandTodos(Pageable pageable) {

		Page<TodoMapper> page = todoRepository.findRecommandTodos(Publish.PUBLISH, pageable);
		return page;
	}

	/**
	 * 
	 * @param pageable
	 * @return
	 */

	@Transactional(readOnly = true)
	public Page<TodoMapper> findMostRecommandDailyTodos(Pageable pageable) {
		
		Page<TodoMapper> page = todoRepository.findDailyTodos(Publish.PUBLISH, pageable);
		return page;
	}
	
//	@Transactional(readOnly = true)
//	public Page<TodoMapper> findRecommandTodos(Pageable pageable) {
//
//		 Page<TodoEntity> pages =
//		 todoRepository.findTodoEntitiesByIsPublishOrderByIdDesc(Publish.PUBLISH,
//		 pageable);
//
//		Page<TodoMapper> page = todoRepository.findRecommandTodos(Publish.PUBLISH, pageable);
//		return page;
//	}

//	@Transactional(readOnly = true)
//	public Page<PostInterface> queryTest(Pageable pageable) {
//
//		return todoRepository.findTodoMainPostByPublish(Publish.PUBLISH, pageable);
//	}

//	@Transactional(readOnly = true)
//	public TodoDTO queryTest() {
//		CriteriaQuery<TodoEntity> criteriaQuery = criteriaBuilder.createQuery(TodoEntity.class);
//		Root<TodoEntity> root = criteriaQuery.from(TodoEntity.class);
//
//		Join<TodoEntity, UserEntity> user = root.join("user", JoinType.INNER);
//		Join<TodoEntity, TodoImageEntity> todoImage = root.join("image", JoinType.INNER);
//
//		criteriaQuery.multiselect(criteriaBuilder.construct(TodoDTO.class, 
//				root.get(""))
//		);

//		return null;
//	}
}
