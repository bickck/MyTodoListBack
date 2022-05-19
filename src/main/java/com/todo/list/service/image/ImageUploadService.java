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
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.hibernate5.SpringBeanContainer;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriBuilder;

import com.todo.list.controller.dto.service.FileDTO;
import com.todo.list.entity.UserBackGroundImageEntity;

@Service
public class ImageUploadService {

	private static final String defaultLocation = "E:\\img" + File.separator;

	@Autowired
	private UserBackGroundImgService backGroundImgService;

	public void saveImageInDir(FileDTO fileDTO) {

		String location = defaultLocation + fileDTO.getUsername();
		Path path = Paths.get(location);
		String originalName = fileDTO.getFile2().getOriginalFilename();
		long fileSize = fileDTO.getFileSize();

		if (Files.isExecutable(path) == false) {
			try {
				Files.createDirectories(path);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		try {
//			InputStream inputStream = multipartFile.getInputStream();
//			Path dirPath = path.resolve(multipartFile.getOriginalFilename());
//			Files.copy(inputStream, dirPath, StandardCopyOption.REPLACE_EXISTING);
		} catch (Exception e) {
			// TODO: handle exception
		}

//		backGroundImgService
//				.userImageSave(new UserBackGroundImageEntity(username, fileName, location, originalName, fileSize));

	}

	public ResponseEntity<?> findUserBackGroundImage(Long imageId, String username) {
		UserBackGroundImageEntity backGroundImageEntity = backGroundImgService.findById(imageId);

		File file = new File(defaultLocation + username + File.separator + backGroundImageEntity.getOriginName());

		return null;
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
