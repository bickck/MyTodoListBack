package com.todo.list.event.record;

import java.nio.channels.CompletionHandler;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.todo.list.entity.log.EventLogRecordEntity;
import com.todo.list.service.EventLogService;

@Component
public class EventLogCallee {

	@Autowired
	private EventLogService eventLogService;
	
	// 스레드를 만드는 함수
	Executor executor = Executors.newFixedThreadPool(30);

	private CompletionHandler<EventLogRecordEntity, Void> completionHandler = new CompletionHandler<EventLogRecordEntity, Void>() {

		@Override
		public void completed(EventLogRecordEntity result, Void attachment) {
			// TODO Auto-generated method stub
			System.out.println("completed");
			eventLogService.save(result);
		}

		@Override
		public void failed(Throwable exc, Void attachment) {
			// TODO Auto-generated method stub
			exc.fillInStackTrace();
		}

	};

	public void callee(EventLogRecordEntity eventLogRecordEntity) {
		
		// CompletableFuture <ReturnType>
		CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(() -> {
			eventLogService.save(eventLogRecordEntity);
		});

//		try {
//			System.out.println("callee");
//			completionHandler.completed(eventLogRecordEntity, null);
//		} catch (Exception e) {
//			// TODO: handle exception
//			completionHandler.failed(e, null);
//		}

	}
}
