package com.todo.list.service.queto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.todo.list.controller.dto.service.QuoteDTO;
import com.todo.list.entity.base.DefaultQuoteEntity;
import com.todo.list.repository.DefaultQuoteRepository;

@Service
public class DefaultQuetoService {

	@Autowired
	private DefaultQuoteRepository quetoRepository;

	@Transactional(readOnly = true)
	public List<DefaultQuoteEntity> getQuotes() {
		return quetoRepository.findAll();
	}
	
	@Transactional
	public String saveQuote() {
		return null;
	}
}
