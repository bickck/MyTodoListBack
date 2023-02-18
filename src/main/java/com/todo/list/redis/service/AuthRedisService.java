package com.todo.list.redis.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todo.list.controller.dto.auth.UserTokenDTO;
import com.todo.list.entity.UserEntity;
import com.todo.list.redis.entity.LoginUserRedisEntity;
import com.todo.list.redis.repository.LoginUserJwtRepository;
import com.todo.list.repository.user.UserRepository;
import com.todo.list.util.auth.provider.AuthenticationJwtProvider;

@Service
public class AuthRedisService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private LoginUserJwtRepository loginUserJwtRepository;

	@Autowired
	private AuthenticationJwtProvider authenticationJwtProvider;

	/**
	 * 
	 * @param payLoad
	 * @return
	 */

	public LoginUserRedisEntity findTokenByPayLoad(String payLoad) {

		Optional<LoginUserRedisEntity> entity = loginUserJwtRepository.findById(payLoad);

		if (entity.isEmpty()) {
			return null;
		}

		return entity.get();

	}

	/**
	 * redis에 로그인하는 정보를 넣기
	 * 
	 * @param accessToken
	 * @param refreshToken
	 * @return accessToken
	 */

	public String saveLoginedUserToken(UserEntity userEntity) {

		String accessToken = authenticationJwtProvider.createAccessToken(userEntity);
		String refreshToken = authenticationJwtProvider.createRefreshToken(userEntity);
		String payload = authenticationJwtProvider.seperatorPayLoad(accessToken);

		LoginUserRedisEntity loginUserRedisEntity = new LoginUserRedisEntity();

		loginUserRedisEntity.setId(payload);
		loginUserRedisEntity.setAccessToken(accessToken);
		loginUserRedisEntity.setRefreshToken(refreshToken);
		loginUserRedisEntity.setAccessAddressIP("");

		loginUserJwtRepository.save(loginUserRedisEntity);

		return accessToken;
	}

	/**
	 * 
	 * @param payLoad
	 */

	public void deleteLoginUserToken(String payLoad) {
		loginUserJwtRepository.deleteById(payLoad);
	}

	/**
	 * 
	 * @param prevPayload
	 * @param refreshToken
	 * @return
	 */

	public String renewAccessTokenByRefreshToken(String prevPayload, String refreshToken) {

		LoginUserRedisEntity loginUserRedisEntity = findTokenByPayLoad(prevPayload);
		deleteLoginUserToken(prevPayload);

		UserTokenDTO refreshTokenDTO = authenticationJwtProvider.resolveTokenToUserTokenDTO(refreshToken);

		UserEntity userEntity = new UserEntity();
		userEntity.setEmail(refreshTokenDTO.getEmail());
		userEntity.setId(refreshTokenDTO.getId());
		userEntity.setUsername(refreshTokenDTO.getUsername());

		String accessToken = authenticationJwtProvider.createAccessToken(userEntity);

		loginUserRedisEntity.setId(authenticationJwtProvider.seperatorPayLoad(accessToken));
		loginUserRedisEntity.setAccessToken(accessToken);
		loginUserRedisEntity.setRefreshToken(refreshToken);

		loginUserJwtRepository.save(loginUserRedisEntity);

		return accessToken;
	}

}
