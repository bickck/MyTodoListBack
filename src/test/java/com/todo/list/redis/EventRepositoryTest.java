package com.todo.list.redis;

import java.util.Iterator;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.todo.list.test.Entity.EventEntity;
import com.todo.list.test.repository.EventRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EventRepositoryTest {

	@Autowired
	private EventRepository eventRepository;

	// @Test
	public void testBatchSave() {
		for (int i = 0; i < 10000; i++) {
			long id = i;
			eventRepository.save(new EventEntity(id));
		}
	}

	@Test
	public void testRedisRepositorySave() {
		long eventId = 1;
		eventRepository.save(new EventEntity(eventId));
	}

	@Test
	public void testRedisFindOne() {
		long Id = 1;
		Iterator<EventEntity> itr = eventRepository.findAll().iterator();

		while (itr.hasNext()) {
			System.out.println(itr.next().toString());
		}
	}

	// @Test
	public void testRedisRepositoryFindAll() {
		eventRepository.findAll().forEach((data) -> {
			System.out.println("hi");
			System.out.println(data);
		});

	}

	// @Test
	public void testRedisDelete() {
		long Id = 1;
		eventRepository.delete(new EventEntity(Id));
	}

}
