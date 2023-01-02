package com.todo.list.test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todo.list.controller.dto.auth.UserTokenDTO;
import com.todo.list.entity.MessageEntity;
import com.todo.list.message.EventMessage;
import com.todo.list.repository.QuoteRepository;
import com.todo.list.repository.event.EventMessageRepository;

@Service
public class CustomAnnotationTestService {

	@Autowired
	private EventMessageRepository eventMessageRepository;

	@EventMessage(message = "Test Message", repositoryClass = QuoteRepository.class)
	public void searchUserTokenDTOArgAtEventMessageAnnotation(UserTokenDTO userTokenDTO, String testArg1,
			String testArg2) {

	}

	public List<MessageEntity> findAllMessage() {

		return eventMessageRepository.findAll();

	}
}
