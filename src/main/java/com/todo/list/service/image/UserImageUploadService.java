package com.todo.list.service.image;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.apache.catalina.User;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.hibernate5.SpringBeanContainer;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriBuilder;

import com.todo.list.controller.dto.service.FileDTO;
import com.todo.list.entity.UserBackGroundImageEntity;
import com.todo.list.repository.UserRepository;
import com.todo.list.service.api.UserApiService;
import com.todo.list.service.image.user.UserBackGroundImgService;
import com.todo.list.service.user.UserService;


public class UserImageUploadService implements ImageService {

	@Override
	public void saveImageInDir(FileDTO fileDTO) {

		String location = DEFAULT_PATH + fileDTO.getUsername();
		Path path = Paths.get(location);

		if (Files.isExecutable(path) == false) {
			try {
				Files.createDirectories(path);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		try {
			InputStream inputStream = fileDTO.getMultipartFile().getInputStream();
			Path dirPath = path.resolve(fileDTO.getMultipartFile().getOriginalFilename());
			Files.copy(inputStream, dirPath, StandardCopyOption.REPLACE_EXISTING);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Override
	public Resource findBackGroundImageInDir(String originalName, String folderName) {

		String path = DEFAULT_PATH + folderName + File.separator + originalName;
		return new FileSystemResource(path);
	}

	@Override
	public boolean deleteBackGroundImageInDir(Long id) {

//		try {
//			if (file.exists()) {
//				if (file.delete()) {
//					System.out.println("삭제 성공");
//				} else {
//
//				}
//
//			} else {
//
//			}
//		} catch (Exception e) {
//			// TODO: handle exception
//		}

		return false;
	}

}
