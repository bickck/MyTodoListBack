package com.todo.list.service.image.admin;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.todo.list.controller.dto.ImageInfoDTO;
import com.todo.list.entity.base.AdminImageEntity;
import com.todo.list.repository.AdminImageRepository;
import com.todo.list.repository.mapper.ImageInfoMapper;
import com.todo.list.service.image.AdminImageUploadService;
import com.todo.list.service.image.ImageService;

@Service
public class MainBackGroundImageService {

	@Autowired
	private AdminImageRepository imageRepository;
	private ImageService adminImageUploadService = new AdminImageUploadService();

	public String save(MultipartFile multipartFile) {
		imageRepository.save(null);
		return null;
	}

	public void delete(Long id) {

		imageRepository.deleteById(id);
	}

	@Transactional(readOnly = true)
	public List<AdminImageEntity> backGroundImages() {

		return imageRepository.findAll();
	}

	@Transactional(readOnly = true)
	public List<ImageInfoMapper> imageNameAndPathList() {

		return imageRepository.findOriginalFileNameAndFilePath();
	}

	@Transactional(readOnly = true)
	public Resource getResource(String originalFileName) throws FileNotFoundException {

		if (imageRepository.existsByOriginalFileName(originalFileName)) {
			return adminImageUploadService.findBackGroundImageInDir(originalFileName, "defaultImage");
		}
		throw new FileNotFoundException();
	}

}
