package com.todo.list.redis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todo.list.redis.entity.MessageChannelEntity;
import com.todo.list.redis.repository.MessageChannelRepository;
import com.todo.list.service.EventMessageService;
import com.todo.list.service.api.UserApiService;

@Service
public class MessageChannelService {

	@Autowired
	private MessageChannelRepository messageChannelRepository;

	@Autowired
	private UserApiService userApiService;

	@Autowired
	private EventMessageService eventMessageService;

	/**
	 * 
	 * @param userid
	 * @param session
	 */

	public void saveChannel(Long userid, String session) {

		String userMessageChannel = userApiService.findUserPersonalChannelName(userid);

		messageChannelRepository.save(new MessageChannelEntity(userid, userMessageChannel, session));
	}

	/**
	 * 
	 * @param id
	 * @return
	 */

	public boolean isExsistUserInRedis(Long id) {
		MessageChannelEntity messageChannelEntity = messageChannelRepository.findById(id).get();

		if (messageChannelEntity.equals(null)) {
			return false;
		}

		return true;
	}

	/**
	 * 
	 * @param id
	 * @return
	 */

	public MessageChannelEntity findUserChannelInfo(Long id) {
		return messageChannelRepository.findById(id).get();
	}
	
	public void deleteUserChannelById(Long id) {
		messageChannelRepository.deleteById(id);
	}

}
