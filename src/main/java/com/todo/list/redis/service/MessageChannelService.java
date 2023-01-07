package com.todo.list.redis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todo.list.redis.entity.MessageChannelEntity;
import com.todo.list.redis.repository.MessageChannelRepository;

@Service
public class MessageChannelService {

	@Autowired
	private MessageChannelRepository messageChannelRepository;

	public void saveChannel(Long userid, String userMessageChannel, String session) {

		messageChannelRepository.save(new MessageChannelEntity(userid, userMessageChannel, session));

	}

	public boolean isExsistUserInRedis(Long id) {
		MessageChannelEntity messageChannelEntity = messageChannelRepository.findById(id).get();

		if (messageChannelEntity.equals(null)) {
			return false;
		}

		return true;
	}

}
