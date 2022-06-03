package com.todo.list.service.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.todo.list.repository.UserQuoteRepository;

@Service
public class QuoteApiService {

	@Autowired
	private UserQuoteRepository quoteRepository;

	@Transactional(readOnly = true)
	public void usersQuoteList() {

	}
}
