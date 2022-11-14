package com.todo.list.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todo.list.repository.UserRepository;

@Service
public class ValidService {

	@Autowired
	private UserRepository userRepository;

	public boolean emailDuplicationCheck(String email) {

		return userRepository.existsByEmail(email);
	}
}
