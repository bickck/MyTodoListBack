package com.todo.list.test;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;
import javax.persistence.Entity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.todo.list.entity.Publish;
import com.todo.list.entity.UserEntity;
import com.todo.list.entity.QuoteEntity;
import com.todo.list.entity.TodoEntity;
import com.todo.list.entity.base.AdminImageEntity;
import com.todo.list.entity.base.AdminQuoteEntity;
import com.todo.list.repository.AdminImageRepository;
import com.todo.list.repository.AdminQuoteRepository;
import com.todo.list.repository.UserQuoteRepository;
import com.todo.list.repository.UserRepository;
import com.todo.list.util.UserUtil;

@Component
public class DummyData implements ApplicationRunner {
//  implements ApplicationListener<ContextRefreshedEvent>
	@Autowired
	private UserRepository repository;

	@Autowired
	private AdminQuoteRepository defaultQuoteRepository;

	@Autowired
	private AdminImageRepository defaultImageRepository;

	@Autowired
	private UserQuoteRepository userQuoteRepository;

	@Autowired
	private UserUtil userUtil;

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public void run(ApplicationArguments args) throws Exception {
		// TODO Auto-generated method stub
		//insertDummyUser();
		//insertDefaultBackGroundImage();

	}

	public void insertDefaultBackGroundImage() {
		File[] file = new File("E:\\img\\defaultImage").listFiles();
		List<AdminImageEntity> lists = new ArrayList<AdminImageEntity>();

		for (File file2 : file) {
			lists.add(new AdminImageEntity(file2.getName(), file2.getAbsolutePath().substring(7)));
		}
		defaultImageRepository.saveAll(lists);

	}

	public void insertDummyUser() {

		List<UserEntity> lists = new ArrayList<UserEntity>();

		for (int i = 0; i < 50000; i++) {

			UserEntity entity = new UserEntity("username" + i, userUtil.bCrypt("1234" + i));
			entity.setQuotes(insertDummyQuote(entity));
			entity.setTodos(insertDummyTodo(entity));
			repository.save(entity);
		}
	}

	public List<QuoteEntity> insertDummyQuote(UserEntity userEntity) {
		List<QuoteEntity> lists = new ArrayList<QuoteEntity>();
		for (int i = 0; i < 50; i++) {
			lists.add(new QuoteEntity(userEntity, userEntity.getUsername() + "quote" + i,
					userEntity.getUsername() + "author " + i, publish(), randomNumber()));
		}
		return lists;
	}

	public List<TodoEntity> insertDummyTodo(UserEntity userEntity) {
		List<TodoEntity> lists = new ArrayList<TodoEntity>();
		for (int i = 0; i < 50; i++) {
			lists.add(new TodoEntity(userEntity, userEntity.getUsername() + "title" + i,
					userEntity.getUsername() + "content" + i, randomNumber(), publish()));
		}
		return lists;
	}

	public void insertDefaultQuote() {

		List<AdminQuoteEntity> list = new ArrayList<AdminQuoteEntity>();
		list.add(new AdminQuoteEntity("나 자신에 대한 자신감을 잃으면 온 세상이 나의 적이 된다.", "토마스 에디슨"));
		list.add(new AdminQuoteEntity("항상 맑으면 사막이 된다. 비가 내리고 바람이불어야만 비옥한 땅이 된다.", " "));
		list.add(new AdminQuoteEntity("인생에서 가장 슬픈 세가지. 할 수 있었는데, 해야 했는데, 해야만 했는데", "루이스 E.분"));
		list.add(new AdminQuoteEntity("같은 실수를 두려워하되 새로운 실수를 두려워하지 말라. 실수는 곧 경험이다.", "도서 '어떤 하루' 中"));
		list.add(new AdminQuoteEntity("오늘은 당신의 남은 인생 중 첫 번째 날이다.", "영화 '아메리칸 뷰티' 中"));
		list.add(new AdminQuoteEntity("인생은 곱셈이다. 어떤 기회가 와도 내가 제로면 아무런 의미가 없다.", "나카무라 미츠루"));
		list.add(new AdminQuoteEntity("별은 바라보는 자에게 빛을 준다.", "도서 ‘드래곤 라자’ 中"));
		list.add(new AdminQuoteEntity("생명이 있는 한 희망이 있다. 실망을 친구로 삼을 것인가, 아니면 희망을 친구로 삼을 것인가.", "J.위트"));
		list.add(new AdminQuoteEntity("실패란 넘어지는 것이 아니라, 넘어진 자리에 머무는 것이다.", "도서 ‘프린세스, 라 브라바!’ 中"));
		list.add(new AdminQuoteEntity("슬픔이 그대의 삶으로 밀려와 마음을 흔들고 소중한 것을 쓸어가 버릴 때면 그대 가슴에 대고 말하라. “이것 또한 지나가리라”",
				"랜터 윌슨 스미스"));

		defaultQuoteRepository.saveAllAndFlush(list);
	}

	public Publish publish() {
		int randomNumber = (int) (Math.random() * 10000) % 10;

		if (randomNumber % 2 == 0) {
			return Publish.PRIVATE;
		} else {
			return Publish.PUBLISH;
		}
	}

	public long randomNumber() {
		return (long) (Math.random() * 10000);
	}

	public int createRandomMonthAndDay(int start, int end) {
		return start + (int) Math.round(Math.random() * (end - start));
	}

	public LocalDate createRandomDate(int startYear, int endYear) {
		int day = createRandomMonthAndDay(1, 28);
		int month = createRandomMonthAndDay(1, 12);
		int year = createRandomMonthAndDay(startYear, endYear);
		return LocalDate.of(year, month, day);
	}
}
