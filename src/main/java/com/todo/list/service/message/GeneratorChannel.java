package com.todo.list.service.message;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class GeneratorChannel {

	@Value(value = "{broker.name.message}")
	private String messageBrokerChannelName;

	public String personalUserMessageChannel(String username) {

		return messageBrokerChannelName + "/" + username;
	}
}
