package com.todo.list.util;

import javax.servlet.http.Cookie;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.todo.list.entity.UserEntity;
import com.todo.list.repository.UserRepository;
import com.todo.list.util.auth.BcryptHelper;

/**
 * @author DongHyeon_kim
 * 
 *         이 문서는 유저 정보를 검사하고 비밀번호를 인코딩하는 등 CRUD 이외의 기능들을 구현한 문서입니다.
 * 
 */
@Component
public class UserUtil implements BcryptHelper {
//
//	@Autowired
//	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private UserRepository userRepository;

	@Override
	public String bCrypt(String password) {
		// TODO Auto-generated method stub
		return BCrypt.hashpw(password, BCrypt.gensalt());
	}

	@Override
	public boolean isMatch(String rowPassword, String encPassword) {
		// TODO Auto-generated method stub
		return BCrypt.checkpw(rowPassword, encPassword);
	}

	public boolean isCheckDuplicatedEmail(String email) {
		UserEntity user = userRepository.findByEmail(email);

		return (user == null) ? true : false;
	}

	public boolean isPasswordValidCheck(String password) {
		return true;
	}

	@Deprecated
	public boolean isFindCookie(Cookie[] cookies, String target) {
		int i = 0;
		if (cookies.length == 0)
			return false;

		while (i < cookies.length) {
			Cookie cookie = cookies[i];
			if (target.equals(cookie.getName())) {
				return true;
			}

		}
		return false;
	}

	@Deprecated
	public int findCookieIndex(Cookie[] cookies, String target) {

		int i = 0;
		if (cookies.length == 0)
			return -1;

		while (i < cookies.length) {
			Cookie cookie = cookies[i];
			if (target.equals(cookie.getName())) {
				return i;
			}

		}
		return -1;
	}
}
