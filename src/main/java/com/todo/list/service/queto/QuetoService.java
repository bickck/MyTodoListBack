package com.todo.list.service.queto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.todo.list.domain.QuetoEntity;
import com.todo.list.repository.QuoteRepository;

@Service
public class QuetoService {

	@Autowired
	private QuoteRepository quetoRepository;

	@Transactional(readOnly = true)
	public List<QuetoEntity> getQuotes() {
		return quetoRepository.findAll();
	}
}
