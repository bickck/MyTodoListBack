package com.todo.list.service.admin;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.todo.list.controller.dto.QuoteDTO;
import com.todo.list.entity.base.AdminQuoteEntity;
import com.todo.list.repository.admin.AdminQuoteRepository;

@Service
public class DefaultQuetoService {

	@Autowired
	private AdminQuoteRepository quetoRepository;

	@Transactional(readOnly = true)
	public List<AdminQuoteEntity> getQuotes() {
		return quetoRepository.findAll();
	}

	@Transactional(readOnly = true)
	public int getQuotesTotalSize(Pageable pageable) {

		return quetoRepository.findAll(pageable).getTotalPages();
	}

	@Transactional
	public AdminQuoteEntity saveQuote(QuoteDTO quoteDTO) {

		return quetoRepository.save(new AdminQuoteEntity(quoteDTO));
	}

	@Transactional
	public AdminQuoteEntity updateQuote(QuoteDTO quoteDTO) {

		return quetoRepository.save(new AdminQuoteEntity(quoteDTO));
	}

	@Transactional
	public void deleteQuoteById(Long id) {
		quetoRepository.deleteById(id);
	}
}
