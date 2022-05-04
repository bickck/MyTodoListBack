package com.todo.list.service.image;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.SpringBeanContainer;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.todo.list.controller.dto.FileDTO;
import com.todo.list.domain.UserBackGroundImageEntity;

@Service
public class ImageUploadService {

	private static final String defaultLocation = "E:\\img" + File.separator;

	@Autowired
	private UserBackGroundImgService backGroundImgService;

	public void saveImageInDir(MultipartFile multipartFile, String username, String fileName) {
		String location = defaultLocation + username;
		Path path = Paths.get(location);
		String originalName = multipartFile.getOriginalFilename();
		long fileSize = multipartFile.getSize();

		if (Files.isExecutable(path) == false) {
			try {
				Files.createDirectories(path);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			InputStream inputStream = multipartFile.getInputStream();
			Path dirPath = path.resolve(multipartFile.getOriginalFilename());
			Files.copy(inputStream, dirPath, StandardCopyOption.REPLACE_EXISTING);
		} catch (Exception e) {
			// TODO: handle exception
		}

		backGroundImgService
				.userImageSave(new UserBackGroundImageEntity(username, fileName, location, originalName, fileSize));

	}

	public void findUserBackGroundImage(Long imageId, String username) {
		UserBackGroundImageEntity backGroundImageEntity = backGroundImgService.findById(imageId);

		File file = new File(defaultLocation + username + File.separator + backGroundImageEntity.getOriginName());
		try {
			File destFile = file.getCanonicalFile();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public String userBackGroundDelete(Long id) {

		UserBackGroundImageEntity backGroundImageEntity = backGroundImgService.userImgDelete(id);
		File file = new File(defaultLocation + backGroundImageEntity.getUser() + File.separator
				+ backGroundImageEntity.getOriginName());

		try {
			if (file.exists()) {
				if (file.delete()) {
					System.out.println("삭제 성공");
				} else {

				}

			} else {

			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		return "";
	}
}
