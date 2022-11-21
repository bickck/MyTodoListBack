package com.todo.list.service.user;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.security.sasl.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.todo.list.controller.dto.user.UserDTO;
import com.todo.list.entity.UserEntity;
import com.todo.list.entity.UserImageEntity;
import com.todo.list.entity.QuoteEntity;
import com.todo.list.repository.TodoRepository;
import com.todo.list.repository.UserRepository;
import com.todo.list.repository.image.UserImageRepository;
import com.todo.list.util.UserUtil;

/**
 * @author DongHyeon_kim
 * 
 *         이 문서는 유저의 로그인, 회원가입 유저의 정보 수정등을 제공하는 클래스
 */

@Service
public class UserService {

	private UserRepository userRepository;
	private UserImageRepository userImageRepository;
	private UserUtil userUtil;

	@Autowired
	public UserService(UserRepository userRepository, UserImageRepository userImageRepository, UserUtil userUtil) {
		// TODO Auto-generated constructor stub
		this.userRepository = userRepository;
		this.userImageRepository = userImageRepository;
		this.userUtil = userUtil;
	}

	/**
	 * 
	 * @param userDTO
	 * @return result status 1 : SUCCESS, 0 : FAILURE or ENTITY INFO
	 */

	@Transactional(isolation = Isolation.SERIALIZABLE)
	public UserEntity userSave(UserDTO userDTO) {
		String email = userDTO.getEmail();
		String username = userDTO.getUsername();
		String password = userDTO.getPassword();
		String passwordEncode = userUtil.bCrypt(password);

		System.out.println(userRepository.existsByEmail(email));
		if (userRepository.existsByEmail(email)) {
			throw new IllegalAccessError("중복된 아이디입니다.");
		}

		UserEntity userEntity = userRepository.save(new UserEntity(email, username, passwordEncode));
		userImageRepository.save(new UserImageEntity(userEntity, "", "", "", (long) 0));

		return userEntity;
	}

	/**
	 * 
	 * @param user
	 * @return result status 1 : SUCCESS, 0 : FAILURE or ENTITY INFO
	 */

	@Transactional
	public UserEntity userUpdate(UserEntity user) {
		UserEntity prevUserEntity = userRepository.getById(user.getId());

		prevUserEntity.setIntroComment(user.getIntroComment());

		return userRepository.save(prevUserEntity);
	}

	/**
	 * 
	 * @param userID and Email
	 */

	@Transactional
	public void userDelete(Long id) {
		// userRepository.deleteByUsernameAndPassword(userDTO.getUsername(),
		// userDTO.getPassword());
		userRepository.deleteById(id);
	}

	// 이메일 확인 및 비밀번호 확인 로직 필요

	/**
	 * 
	 * @param requestUserArg
	 * @throws AuthenticationException
	 * @return result status 1 : SUCCESS, 0 : FAILURE or ENTITY INFO
	 */

	@Transactional
	public UserEntity userLogin(UserDTO requestUserArg) throws AuthenticationException {

		UserEntity user = userRepository.findByEmail(requestUserArg.getEmail());

		String password = requestUserArg.getPassword();
		String encPassword = user.getPassword();

		if (!userUtil.isMatch(password, encPassword) && user != null) {
			throw new AuthenticationException("이메일 및 비밀번호가 맞지 않습니다.");
		}

		return user;
	}

	/**
	 * 
	 * @param id
	 * @param requestUserArg
	 * @return result status 1 : SUCCESS, 0 : FAILURE or ENTITY INFO
	 */
	@Transactional
	public UserEntity changeUserPassword(Long id, UserDTO requestUserArg) {

		UserEntity user = userRepository.findByEmail(requestUserArg.getEmail());

		String encPassword = userUtil.bCrypt(requestUserArg.getPassword());
		user.setPassword(encPassword);

		return userRepository.save(user);
	}

}
