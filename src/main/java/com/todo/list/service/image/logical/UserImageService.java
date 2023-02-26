package com.todo.list.service.image.logical;

import com.todo.list.service.image.physical.ImageUploader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.todo.list.entity.UserImageEntity;
import com.todo.list.entity.UserEntity;
import com.todo.list.repository.image.UserImageRepository;
import com.todo.list.util.uuid.CommonUUID;

@Service
public class UserImageService {

	private UserImageRepository imageRepository;

	@Autowired
	private ImageUploader imageUploader;

	@Autowired
	public UserImageService(UserImageRepository imageRepository) {
		this.imageRepository = imageRepository;

	}

	/**
	 * 
	 * @param userEntity : User Info
	 * @return
	 */

	public UserEntity saveRegistedUserImage(UserEntity userEntity) {

		String createImageUUID = new CommonUUID().generatorImageUUID();

		userImageSave(new UserImageEntity(userEntity, createImageUUID, "", "", "DEFAULT"));

		return userEntity;
	}

	/**
	 * 
	 * @param userImageEntity : User Info
	 * @return
	 */

	@Transactional(rollbackFor = Exception.class)
	public UserImageEntity userImageSave(UserImageEntity userImageEntity) {
		return imageRepository.save(userImageEntity);
	}

	/**
	 * 
	 * @param id
	 */

	@Transactional(rollbackFor = Exception.class)
	public void deleteUserIntroImage(Long id) {
		UserImageEntity userIntroImage = imageRepository.getById(id);
		imageRepository.deleteById(id);
	}

	/**
	 * 
	 * @param id
	 * @return
	 */

	@Transactional(readOnly = true)
	public UserImageEntity findUserImageByUserId(Long id) {
		return imageRepository.findUserIntroImageByUserId(id);
	}

	/**
	 * 
	 * @param userIntroImage
	 *
	 */

	@Transactional(rollbackFor = Exception.class)
	public void updateDefaultUserIntroImage(UserImageEntity userIntroImage) {

		userIntroImage.setFileName("DEFAULT");

		userIntroImage.setFilePath("");

		userIntroImage.setFileSize((long) 0);

		userIntroImage.setOriginalFileName("");

		imageRepository.save(userIntroImage);

	}
}
