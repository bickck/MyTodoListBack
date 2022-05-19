package com.todo.list.admin;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.todo.list.controller.dto.service.QuoteDTO;
import com.todo.list.entity.base.DefaultQuoteEntity;
import com.todo.list.repository.DefaultQuoteRepository;

@Service
public class QuetoManagementService {

	@Autowired
	private DefaultQuoteRepository quetoRepository;

	
	public void quetoSave(QuoteDTO dto) {
		quetoRepository.save(new DefaultQuoteEntity(dto));
	}

}
