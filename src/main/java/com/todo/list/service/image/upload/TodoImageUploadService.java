package com.todo.list.service.image.upload;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.todo.list.controller.dto.ImageDTO;
import com.todo.list.file.FileSupport;
import com.todo.list.file.FileSupportImpl;
import com.todo.list.service.image.ImageUploadService;
import com.todo.list.service.image.user.TodoImageService;
import com.todo.list.util.uuid.CommonUUID;

@Service
public class TodoImageUploadService {

	@Value(value = "${image.path.todo}")
	private String physicalAddress;

	private CommonUUID commonUUID = new CommonUUID();
	private FileSupport fileSupport = new FileSupportImpl();

	/**
	 * 
	 */

	public ImageDTO saveImageInDir(MultipartFile todoImage) {
		// TODO Auto-generated method stub

		String originalFileName = todoImage.getOriginalFilename();
		String fileName = commonUUID.generatorImageUUID();
		String filePath = fileSupport.generatorFilePath(fileName, physicalAddress);
		Long fileSize = todoImage.getSize();

		Path path = Paths.get(filePath);

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

	public Resource findImageInDirectory(String originalFileName, String filePath) {
		// TODO Auto-generated method stub

		String path = filePath + File.separator + originalFileName;

		return new FileSystemResource(path);
	}

	/**
	 * 
	 * 
	 */

	public boolean deleteImageInDirectory(String originalName, String folderName) throws IOException {
		// TODO Auto-generated method stub
		Resource resource = new FileSystemResource(
				Path.of(physicalAddress + folderName + File.separator + originalName));

		return resource.getFile().delete();
	}

}
