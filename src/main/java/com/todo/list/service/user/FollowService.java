package com.todo.list.service.user;

import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todo.list.entity.UserEntity;
import com.todo.list.entity.UserFollowEntity;
import com.todo.list.repository.user.UserFollowRepository;
import com.todo.list.repository.user.UserRepository;

@Service
public class FollowService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserFollowRepository userFollowRepository;

	public void saveUserFollow(Long userId) {

		UserEntity userEntity = userRepository.findById(userId)
				.orElseThrow(() -> new NullPointerException("존재하지 않는 아이디입니다."));
		
		
		
		userFollowRepository.save(new UserFollowEntity());

	}

	public void deleteUserFollow() {

	}
}
