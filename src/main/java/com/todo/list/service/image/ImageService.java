package com.todo.list.service.image;

import java.io.File;
import java.io.IOException;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.todo.list.controller.dto.FileDTO;

public interface ImageService {

	static String DEFAULT_PATH = "E:\\img" + File.separator;

	void saveImageInDir(FileDTO filedto);

	Resource findBackGroundImageInDir(String originalName, String folderName);

	boolean deleteBackGroundImageInDir(String originalName, String folderName) throws IOException;

	//boolean existsImage(String originalName, String folderName);

}
