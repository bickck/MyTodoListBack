package com.todo.list.test;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

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
import com.todo.list.repository.UserRepository;
import com.todo.list.util.UserUtil;

@Component
public class DummyData implements ApplicationRunner {
//  implements ApplicationListener<ContextRefreshedEvent>
	@Autowired
	private UserRepository repository;

	@Autowired
	private UserUtil userUtil;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		// TODO Auto-generated method stub
//		System.out.println("init() ");
//
//		List<UserEntity> lists = new ArrayList<UserEntity>();
//		for (int i = 0; i < 100; i++) {
//			lists.add(new UserEntity("username" + i, userUtil.passwordEncoding("1234" + i)));
//		}
//		repository.saveAll(lists);
	}
}
