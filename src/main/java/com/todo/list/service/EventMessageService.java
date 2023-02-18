package com.todo.list.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todo.list.entity.MessageEntity;
import com.todo.list.entity.QuoteEntity;
import com.todo.list.entity.TodoEntity;
import com.todo.list.entity.UserEntity;
import com.todo.list.repository.event.EventMessageRepository;
import com.todo.list.repository.quote.QuoteRepository;
import com.todo.list.repository.todo.TodoRepository;
import com.todo.list.repository.user.UserRepository;

@Service
public class EventMessageService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private QuoteRepository quoteRepository;

	@Autowired
	private TodoRepository todoRepository;

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

	public UserEntity findUserEntityByTodoId(Long todoId) {

		Optional<TodoEntity> optional = todoRepository.findById(todoId);

		return optional.get().getUser();
	}

	public UserEntity findUserEntityByQuoteId(Long quoteId) {

		Optional<QuoteEntity> optional = quoteRepository.findById(quoteId);

		return optional.get().getUser();
	}

	public String findPersonalUserChannelByUserId(Long userId) {

		Optional<UserEntity> optional = userRepository.findById(userId);

		return optional.get().getPersonalMessageChannelName();
	}

	public void deleteUserMessageById(Long id) {
		eventMessageRepository.deleteByUserId(id);
	}
}
