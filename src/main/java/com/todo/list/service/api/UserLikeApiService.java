package com.todo.list.service.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.todo.list.repository.QuoteLikeRepository;
import com.todo.list.repository.TodoLikeRepository;


@Service
public class UserLikeApiService {

	@Autowired
	private TodoLikeRepository todoLikeRepository;
	
	@Autowired
	private QuoteLikeRepository quoteLikeRepository;

	
	/**
	 * 
	 */
	
	@Transactional(readOnly = true)
	public void userLikeTodo() {

	}

	/**
	 * 
	 */
	
	@Transactional(readOnly = true)
	public void userLikeQuote() {

	}
}
