package com.todo.list.service.image.physical;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import com.todo.list.controller.dto.ImageDTO;

public interface ImageUploader {

	ImageDTO saveImageInDir(MultipartFile images);

	Resource findImageInDirectory(String originalName, String folderName);

	boolean deleteImageInDirectory(String originalName, String folderName) throws Exception;

}
