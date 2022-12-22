package com.todo.list.service.admin;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.todo.list.entity.base.AdminImageEntity;
import com.todo.list.repository.admin.AdminImageRepository;
import com.todo.list.repository.mapper.ImageMapper;
import com.todo.list.service.image.ImageUploadService;
import com.todo.list.service.image.upload.TodoImageUploadService;

@Service
public class MainBackGroundImageService {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	private final static String cacheName = "cacheStorage";
	private final static String DEFAULT_FOLDER = "defaultImage";

	@Autowired
	private AdminImageRepository imageRepository;

	@Transactional
	public String save(MultipartFile multipartFile) {
		imageRepository.save(null);
		return null;
	}

//	public void delete(Long id, String filename) {
//
//		try {
//			if (adminImageUploadService.deleteBackGroundImageInDir(filename, DEFAULT_FOLDER))
//				imageRepository.deleteById(id);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//
//	@Transactional(readOnly = true)
//	public List<AdminImageEntity> backGroundImages() {
//
//		return imageRepository.findAll();
//	}
//
//	@Transactional(readOnly = true)
//	public List<AdminImageEntity> imageNames() {
//
//		return imageRepository.findAll();
//	}
//
//	@Cacheable(key = "#originalFileName", cacheNames = cacheName)
//	@Transactional(readOnly = true)
//	public Resource getResource(String originalFileName) throws FileNotFoundException {
//
//		if (imageRepository.existsByOriginalFileName(originalFileName)) {
//			return adminImageUploadService.findBackGroundImageInDir(originalFileName, DEFAULT_FOLDER);
//		}
//		throw new FileNotFoundException();
//	}

}
