package com.todo.list.service.image.upload;

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
import org.springframework.web.multipart.MultipartFile;

import com.todo.list.controller.dto.ImageDTO;
import com.todo.list.file.FileSupport;
import com.todo.list.file.FileSupportImpl;
import com.todo.list.service.admin.MainBackGroundImageService;
import com.todo.list.service.image.ImageUploadService;
import com.todo.list.service.image.user.TodoImageService;
import com.todo.list.util.uuid.CommonUUID;

@Service
public class TodoImageUploadService implements ImageUploadService {

	private CommonUUID commonUUID = new CommonUUID();
	private FileSupport fileSupport = new FileSupportImpl();

	/**
	 * 
	 */

	@Override
	public ImageDTO saveImageInDir(MultipartFile todoImage) {
		// TODO Auto-generated method stub

		String originalFileName = todoImage.getOriginalFilename();
		String fileName = commonUUID.generatorImageUUID();
		String filePath = fileSupport.generatorFilePath(fileName);
		Long fileSize = todoImage.getSize();

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
			InputStream inputStream = todoImage.getInputStream();
			Path dirPath = path.resolve(todoImage.getOriginalFilename());
			Files.copy(inputStream, dirPath, StandardCopyOption.REPLACE_EXISTING);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return new ImageDTO(fileName, originalFileName, filePath, fileSize);
	}

	/**
	 * 
	 */

	@Override
	public Resource findImageInDirectory(String originalFileName, String filePath) {
		// TODO Auto-generated method stub

		String path = DEFAULT_PATH + filePath + File.separator + originalFileName;

		return new FileSystemResource(path);
	}

	/**
	 * 
	 * 
	 */

	@Override
	public boolean deleteBackGroundImageInDirectory(String originalName, String folderName) throws IOException {
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
