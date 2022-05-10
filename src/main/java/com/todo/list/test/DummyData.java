package com.todo.list.test;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.todo.list.entity.UserEntity;
import com.todo.list.repository.UserRepository;
import com.todo.list.util.UserUtil;

@Configuration
public class DummyData {

	@Autowired
	private UserRepository repository;

	@Autowired
	private UserUtil userUtil;

	@PostConstruct
	public void init() {
		List<UserEntity> lists = new ArrayList<UserEntity>();
		for (int i = 0; i < 10000; i++) {
			lists.add(new UserEntity("username" + i, userUtil.passwordEncoding("1234" + i)));
		}
		repository.saveAll(lists);
	}
}
