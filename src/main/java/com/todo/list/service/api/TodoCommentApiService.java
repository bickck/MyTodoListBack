package com.todo.list.service.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.todo.list.repository.mapper.TodoCommentMapper;
import com.todo.list.repository.todo.TodoCommentRepository;

@Service
public class TodoCommentApiService {

	private TodoCommentRepository todoCommentRepository;

	@Autowired
	public TodoCommentApiService(TodoCommentRepository todoCommentRepository) {
		this.todoCommentRepository = todoCommentRepository;
	}

	/**
	 * 
	 * @param todo id
	 * @param pageable
	 */

	@Transactional(readOnly = true)
	public Page<TodoCommentMapper> findTodoCommentsByTodoId(Long id, Pageable pageable) {
		
		return todoCommentRepository.findTodoCommentByTodoId(id, pageable);
	}
}
