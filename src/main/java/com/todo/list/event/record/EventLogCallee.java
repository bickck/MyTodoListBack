package com.todo.list.event.record;

import java.nio.channels.CompletionHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.todo.list.service.EventLogService;

@Component
public class EventLogCallee implements CompletionHandler<Object, Void> {

	@Autowired
	private EventLogService eventLogService;

	public void callee() {

	}

	@Override
	public void completed(Object result, Void attachment) {
		// TODO Auto-generated method stub

	}

	@Override
	public void failed(Throwable exc, Void attachment) {
		// TODO Auto-generated method stub

	}
}
