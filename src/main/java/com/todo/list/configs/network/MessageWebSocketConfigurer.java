package com.todo.list.configs.network;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketConfigurationSupport;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurationSupport;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import com.todo.list.interceptor.PersonalChannelInterceptor;

@Configuration
@EnableWebSocketMessageBroker
public class MessageWebSocketConfigurer implements WebSocketMessageBrokerConfigurer {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Value(value = "${broker.server.name}")
	private String weSoketServer;

	@Value(value = "${broker.name.sub}")
	private String subBrokerChannelName;

	@Value(value = "${broker.name.message}")
	private String messageBrokerChannelName;

	@Value(value = "${broker.dest.prex}")
	private String destPrefiexsChannelName;

	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		// TODO Auto-generated method stub
		registry.enableSimpleBroker(subBrokerChannelName, messageBrokerChannelName);
		registry.setApplicationDestinationPrefixes(destPrefiexsChannelName);
	}

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		// TODO Auto-generated method stub
		registry.addEndpoint(weSoketServer).setAllowedOriginPatterns("*").withSockJS();
	}
	
	@Override
	public void configureClientInboundChannel(ChannelRegistration registration) {
		// TODO Auto-generated method stub
		registration.interceptors(new PersonalChannelInterceptor());
	}

//	@Override
//	protected void configureClientInboundChannel(ChannelRegistration registration) {
//		// TODO Auto-generated method stub
//		super.configureClientInboundChannel(registration);
//	}

}
