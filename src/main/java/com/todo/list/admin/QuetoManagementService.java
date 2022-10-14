package com.todo.list.admin;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.todo.list.controller.dto.QuoteDTO;
import com.todo.list.entity.base.AdminQuoteEntity;
import com.todo.list.repository.admin.AdminQuoteRepository;

@Service
public class QuetoManagementService {

	@Autowired
	private AdminQuoteRepository quetoRepository;

	
	@Transactional(isolation = Isolation.DEFAULT)
	public void quetoSave(QuoteDTO dto) {
		quetoRepository.save(new AdminQuoteEntity(dto));
	}

}
