package com.todo.list.jpa;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.ArgumentMatchers.endsWith;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;

import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.todo.list.entity.Publish;
import com.todo.list.entity.UserEntity;
import com.todo.list.entity.QuoteEntity;
import com.todo.list.repository.UserQuoteRepository;
import com.todo.list.repository.TodoRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class QuoteRepositoryQueryTest {

	@Autowired
	private UserQuoteRepository userQuoteRepository;

	private QuoteEntity publishEntity = null;
	private QuoteEntity notPublishEntity = null;

	@Before
	public void saveTestCase() {
		publishEntity = userQuoteRepository.save(new QuoteEntity(new UserEntity("hihello", "password1234"),
				"quote1234", "quote1234", Publish.PUBLISH, (long) 0));

		notPublishEntity = userQuoteRepository.save(new QuoteEntity(new UserEntity("hihello", "password1234"),
				"quote1234", "quote1234", Publish.PRIVATE, (long) 0));
	}

	@Test
	public void getPublishedUserTodo() {
		List<QuoteEntity> entity = userQuoteRepository.findQuoteEntitiesByIsPublish(Publish.PUBLISH);

		List<QuoteEntity> entities = userQuoteRepository.findAll();

		System.out.println(entities.toString());

	}
}
