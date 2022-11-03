package com.todo.list.service.image;

import java.io.File;
import java.io.IOException;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.todo.list.controller.dto.ImageDTO;

public interface ImageUploadService {

	static String DEFAULT_PATH = "E:\\img";
	

	ImageDTO saveImageInDir(MultipartFile todoImage);

	Resource findImageInDirectory(String originalName, String folderName);

	boolean deleteBackGroundImageInDirectory(String originalName, String folderName) throws IOException;

	//boolean existsImage(String originalName, String folderName);

}
