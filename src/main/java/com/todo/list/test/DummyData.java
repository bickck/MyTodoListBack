package com.todo.list.test;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.Entity;

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

import com.todo.list.entity.UserEntity;
import com.todo.list.entity.UserQuoteEntity;
import com.todo.list.entity.base.DefaultQuoteEntity;
import com.todo.list.repository.DefaultQuoteRepository;
import com.todo.list.repository.UserRepository;
import com.todo.list.util.UserUtil;

@Component
public class DummyData implements ApplicationRunner {
//  implements ApplicationListener<ContextRefreshedEvent>
	@Autowired
	private UserRepository repository;

	@Autowired
	private DefaultQuoteRepository defaultQuoteRepository;
	@Autowired
	private UserUtil userUtil;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		// TODO Auto-generated method stub
//		System.out.println("init() ");
//
//		List<UserEntity> lists = new ArrayList<UserEntity>();
//		for (int i = 0; i < 100; i++) {
//			UserEntity entity = new UserEntity("username" + i, userUtil.bCrypt("1234" + i));
//
//			List<UserQuoteEntity> entities = new ArrayList<UserQuoteEntity>();
//			for (int j = 0; j < 5; j++) {
//				entities.add(new UserQuoteEntity(entity, "quote" + j, "author" + j));
//			}
//			entity.setQuotes(entities);
//			lists.add(entity);
//		}
//		repository.saveAll(lists);
//
//		List<DefaultQuoteEntity> list = new ArrayList<DefaultQuoteEntity>();
//		list.add(new DefaultQuoteEntity("나 자신에 대한 자신감을 잃으면 온 세상이 나의 적이 된다.", "토마스 에디슨"));
//		list.add(new DefaultQuoteEntity("항상 맑으면 사막이 된다. 비가 내리고 바람이불어야만 비옥한 땅이 된다.", " "));
//		list.add(new DefaultQuoteEntity("인생에서 가장 슬픈 세가지. 할 수 있었는데, 해야 했는데, 해야만 했는데", "루이스 E.분"));
//		list.add(new DefaultQuoteEntity("같은 실수를 두려워하되 새로운 실수를 두려워하지 말라. 실수는 곧 경험이다.", "도서 '어떤 하루' 中"));
//		list.add(new DefaultQuoteEntity("오늘은 당신의 남은 인생 중 첫 번째 날이다.", "영화 '아메리칸 뷰티' 中"));
//		list.add(new DefaultQuoteEntity("인생은 곱셈이다. 어떤 기회가 와도 내가 제로면 아무런 의미가 없다.", "나카무라 미츠루"));
//		list.add(new DefaultQuoteEntity("별은 바라보는 자에게 빛을 준다.", "도서 ‘드래곤 라자’ 中"));
//		list.add(new DefaultQuoteEntity("생명이 있는 한 희망이 있다. 실망을 친구로 삼을 것인가, 아니면 희망을 친구로 삼을 것인가.", "J.위트"));
//		list.add(new DefaultQuoteEntity("실패란 넘어지는 것이 아니라, 넘어진 자리에 머무는 것이다.", "도서 ‘프린세스, 라 브라바!’ 中"));
//		list.add(new DefaultQuoteEntity("슬픔이 그대의 삶으로 밀려와 마음을 흔들고 소중한 것을 쓸어가 버릴 때면 그대 가슴에 대고 말하라. “이것 또한 지나가리라”",
//				"랜터 윌슨 스미스"));
//
//		defaultQuoteRepository.saveAllAndFlush(list);
	}
}
