package com.todo.list.service.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.todo.list.repository.user.UserRepository;

@Service
public class AdminApiService {

	@Autowired
	private UserRepository userRepository;
	
	@Transactional(readOnly = true)
	public long allUserCount() {
	
		return userRepository.count();
	}
	
	public int joinedUserToday() {
		return 0;
	}
}
