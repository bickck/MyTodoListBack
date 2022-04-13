package com.todo.list.service.util;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.todo.list.domain.UserEntity;
import com.todo.list.repository.UserRepository;

/**
 * @author DongHyeon_kim
 * 
 *         이 문서는 유저 정보를 검사하고 비밀번호를 인코딩하는 등 CRUD 이외의 기능들을 구현한 문서입니다.
 * 
 */
@Component
public class UserUtil {

//	@Autowired
//	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private UserRepository userRepository;

//	public String passwordEncoding(String password) {
//		//String encodePassword = bCryptPasswordEncoder.encode(password);
//		return encodePassword;
//	}

	public boolean isUsernameDuplicatedCheck(String username) {
		UserEntity user = userRepository.findByUsername(username);
		if (user == null) {
			return false;
		}
		return true;
	}

}
