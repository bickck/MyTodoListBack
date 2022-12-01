package com.todo.list.service.image.upload;

/**
 * 
 * 하드디스크에 실제 이미지 저장
 */

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.todo.list.controller.dto.ImageDTO;
import com.todo.list.file.FileSupport;
import com.todo.list.file.FileSupportImpl;
import com.todo.list.service.image.ImageUploadService;
import com.todo.list.util.uuid.CommonUUID;

public class UserImageUploadService implements ImageUploadService {

	private CommonUUID commonUUID = new CommonUUID();
	private FileSupport fileSupport = new FileSupportImpl();
	

	/**
	 * 
	 * 
	 */

	@Override
	public ImageDTO saveImageInDir(MultipartFile userImage) {
		// TODO Auto-generated method stub

		String originalFileName = userImage.getOriginalFilename();
		String fileName = commonUUID.generatorImageUUID();
		String filePath = fileSupport.generatorFilePath(fileName);
		Long fileSize = userImage.getSize();

		Path path = Paths.get(DEFAULT_PATH + filePath);

		if (Files.isExecutable(path) == false) {
			try {
				Files.createDirectories(path);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		try {
			InputStream inputStream = userImage.getInputStream();
			Path dirPath = path.resolve(userImage.getOriginalFilename());
			Files.copy(inputStream, dirPath, StandardCopyOption.REPLACE_EXISTING);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 
	 * 
	 */

	@Override
	public Resource findImageInDirectory(String originalName, String folderName) {

		String path = DEFAULT_PATH + folderName + File.separator + originalName;

		return new FileSystemResource(path);
	}

	/**
	 * @throws Exception
	 * 
	 * 
	 */

	@Override
	public boolean deleteImageInDirectory(String originalName, String folderName) throws Exception {

		Resource resource = new FileSystemResource(Path.of(DEFAULT_PATH + folderName + File.separator + folderName));

		try {

		} catch (Exception e) {
			// TODO: handle exception
			throw new Exception();
		}
		
		return resource.getFile().delete();
	}

}
