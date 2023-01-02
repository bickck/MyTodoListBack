package com.todo.list.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todo.list.entity.MessageEntity;
import com.todo.list.entity.UserEntity;
import com.todo.list.repository.event.EventMessageRepository;

@Service
public class MessageService {

	@Autowired
	private EventMessageRepository eventMessageRepository;

	public void saveMessage(String message, String to, String from, Long userId) {

		MessageEntity entity = new MessageEntity();

		entity.setUserid(userId);
		entity.setTo(to);
		entity.setFrom(from);
		entity.setMessage(message);

		eventMessageRepository.save(entity);
	}

	public void saveMessage(String message, Long userId) {

		MessageEntity entity = new MessageEntity();

		entity.setUserid(userId);
		entity.setTo("");
		entity.setFrom("");
		entity.setMessage(message);

		eventMessageRepository.save(entity);
	}
}
