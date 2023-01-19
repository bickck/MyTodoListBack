package com.todo.list.service.user;

import javax.security.sasl.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.todo.list.controller.dto.ImageDTO;
import com.todo.list.controller.dto.auth.UserTokenDTO;
import com.todo.list.controller.dto.user.UserDTO;
import com.todo.list.entity.UserEntity;
import com.todo.list.entity.UserImageEntity;
import com.todo.list.entity.base.PlatForm;
import com.todo.list.redis.service.AuthRedisService;
import com.todo.list.repository.UserRepository;
import com.todo.list.repository.image.UserImageRepository;
import com.todo.list.service.image.upload.UserImageUploadService;
import com.todo.list.service.image.user.UserImageService;
import com.todo.list.service.message.GeneratorChannel;
import com.todo.list.util.UserUtil;

/**
 * @author DongHyeon_kim
 * 
 *         이 문서는 유저의 로그인, 회원가입 유저의 정보 수정등을 제공하는 클래스
 */

@Service
public class UserService {

	private UserImageService userImageService;
	private UserRepository userRepository;
	private UserImageRepository userImageRepository;
	private UserImageUploadService imageUploadService;

	@Autowired
	public UserService(UserImageService userImageService, UserRepository userRepository,
			UserImageRepository userImageRepository, UserImageUploadService imageUploadService) {
		this.userImageService = userImageService;
		this.userRepository = userRepository;
		this.userImageRepository = userImageRepository;
		this.imageUploadService = imageUploadService;
	}

	/**
	 * 
	 * @param user
	 * @return result status 1 : SUCCESS, 0 : FAILURE or ENTITY INFO
	 */

	@Transactional
	public UserEntity userUpdate(UserDTO userDTO, UserTokenDTO userTokenDTO) {

		UserEntity userEntity = userRepository.findById(userTokenDTO.getId()).get();

		if (userDTO.getIntroComment() != null) {
			userEntity.setIntroComment(userDTO.getIntroComment());
		}
		if (userDTO.getNickName() != null) {

		}
		if (userDTO.getBirth() != null) {

		}

		return userRepository.save(userEntity);
	}

	/**
	 * 
	 * @param userID and Email
	 */

	@Transactional
	public void userDelete(Long userid) {

		userRepository.deleteById(userid);
	}

	/**
	 * 
	 * @param id
	 * @param requestUserArg
	 * @return result status 1 : SUCCESS, 0 : FAILURE or ENTITY INFO
	 */
	@Transactional(rollbackFor = Exception.class)
	public UserEntity updateUserIntroComment(String comment, UserTokenDTO requestUserArg) {

		UserEntity entity = userRepository.findById(requestUserArg.getId()).get();

		entity.setIntroComment(comment);

		return userRepository.save(entity);
	}

	/**
	 * 
	 * @param userId
	 * @param userImage
	 * @return
	 */

	@Transactional(rollbackFor = Exception.class)
	public UserImageEntity updateUserIntroImage(Long userId, MultipartFile userImage) {

		UserImageEntity userIntroImage = userImageService.findUserImageByUserId(userId);

		ImageDTO imageDTO = imageUploadService.saveImageInDir(userImage);

		userIntroImage.setFileName(imageDTO.getFileName());
		userIntroImage.setFilePath(imageDTO.getFilePath());
		userIntroImage.setOriginalFileName(imageDTO.getOriginName());
		userIntroImage.setFileSize(imageDTO.getFileSize());

		// DB 내용 변경
		return userImageService.userImageSave(userIntroImage);
	}

	/**
	 * 
	 * @param userDTO
	 * @throws Exception
	 */

	@Transactional(rollbackFor = Exception.class)
	public boolean deleteUserIntroImage(UserTokenDTO userDTO) throws Exception {

		Long userid = userDTO.getId();

		UserImageEntity userIntroImage = userImageRepository.findUserIntroImageByUserId(userid);
		// 삭제할 이미지가 디폴트 이미지일 경우 리턴

		if (userIntroImage.getFileName().equals("DEFAULT")) {
			return false;
		}

		try {

			imageUploadService.deleteImageInDirectory(userIntroImage.getOriginalFileName(),
					userIntroImage.getFilePath());

			// 디폴트 유저 이미지는 저장되어있기 때문에 DB에만 정보를 저장함
			userImageService.updateDefaultUserIntroImage(userIntroImage);
		} catch (Exception e) {
			// TODO: handle exception
			throw new Exception();
		}

		return true;
	}

}
