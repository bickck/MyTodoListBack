package com.todo.list.redis.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todo.list.configs.token.AuthenticationJwtProvider;
import com.todo.list.controller.dto.auth.UserTokenDTO;
import com.todo.list.entity.UserEntity;
import com.todo.list.redis.entity.LoginUserRedisEntity;
import com.todo.list.redis.repository.LoginUserJwtRepository;
import com.todo.list.repository.UserRepository;

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
	 * 
	 * @param accessToken
	 * @param refreshToken
	 */

	public void saveRefreshedLoginUserToken(String accessToken, String refreshToken) {

		String payload = authenticationJwtProvider.seperatorPayLoad(accessToken);

		LoginUserRedisEntity loginUserRedisEntity = new LoginUserRedisEntity();

		loginUserRedisEntity.setId(payload);
		loginUserRedisEntity.setAccessToken(accessToken);
		loginUserRedisEntity.setRefreshToken(refreshToken);
		loginUserRedisEntity.setAccessAddressIP("");

		loginUserJwtRepository.save(loginUserRedisEntity);
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
