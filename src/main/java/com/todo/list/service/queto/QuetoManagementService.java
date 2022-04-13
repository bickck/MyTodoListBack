package com.todo.list.service.queto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todo.list.controller.dto.QuoteDTO;
import com.todo.list.domain.QuetoEntity;
import com.todo.list.repository.QuoteRepository;

@Service
public class QuetoManagementService {

	@Autowired
	private QuoteRepository quetoRepository;

	public void quetoSave(QuoteDTO dto) {
		quetoRepository.save(new QuetoEntity(dto));
	}

}
	