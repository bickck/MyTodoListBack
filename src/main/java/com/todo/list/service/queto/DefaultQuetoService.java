package com.todo.list.service.queto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.todo.list.controller.dto.service.QuoteDTO;
import com.todo.list.entity.base.AdminQuoteEntity;
import com.todo.list.repository.AdminQuoteRepository;

@Service
public class DefaultQuetoService {

	@Autowired
	private AdminQuoteRepository quetoRepository;

	@Transactional(readOnly = true)
	public List<AdminQuoteEntity> getQuotes() {
		return quetoRepository.findAll();
	}
	
	@Transactional
	public String saveQuote() {
		return null;
	}
}
