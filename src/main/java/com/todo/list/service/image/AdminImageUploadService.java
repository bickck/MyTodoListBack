package com.todo.list.service.image;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.todo.list.controller.dto.service.FileDTO;
import com.todo.list.service.image.admin.MainBackGroundImageService;

public class AdminImageUploadService implements ImageService {

	@Override
	public void saveImageInDir(FileDTO filedto) {
		// TODO Auto-generated method stub
		Path path = Paths.get(DEFAULT_PATH);
		try {
			InputStream inputStream = filedto.getMultipartFile().getInputStream();
			Path dirPath = path.resolve(filedto.getMultipartFile().getOriginalFilename());
			Files.copy(inputStream, dirPath, StandardCopyOption.REPLACE_EXISTING);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Override
	public Resource findBackGroundImageInDir(String originalName, String folderName) {
		// TODO Auto-generated method stub

		String path = DEFAULT_PATH + folderName + File.separator + originalName;

		return new FileSystemResource(path);
	}

	@Override
	public boolean deleteBackGroundImageInDir(String originalName, String folderName) throws IOException {
		// TODO Auto-generated method stub
		Resource resource = new FileSystemResource(Path.of(DEFAULT_PATH + folderName + File.separator + originalName));
		
		return resource.getFile().delete();
	}

//	@Override
//	public boolean existsImage(String originalName, String folderName) {
//		// TODO Auto-generated method stub
//
//		Resource resource = new FileSystemResource(Path.of(DEFAULT_PATH + folderName + File.separator + folderName));
//		return resource.exists();
//	}
}
