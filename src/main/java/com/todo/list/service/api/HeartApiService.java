package com.todo.list.service.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todo.list.repository.quote.QuoteHeartRepository;
import com.todo.list.repository.todo.TodoHeartRepository;

@Service
public class HeartApiService {

	@Autowired
	private QuoteHeartRepository quoteHeartRepository;
	
	@Autowired
	private TodoHeartRepository todoHeartRepository;
}
