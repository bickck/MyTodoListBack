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
	public ImageDTO saveImageInDir(MultipartFile todoImage) {
		// TODO Auto-generated method stub

		
		String uuid = commonUUID.generatorImageUUID();
		String filePath = fileSupport.generatorFilePath(uuid);
		
		Path path = Paths.get(filePath);

		if (Files.isExecutable(path) == false) {
			try {
				Path directories =  Files.createDirectories(path);			
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

//		try {
//			InputStream inputStream = fileDTO.getMultipartFile().getInputStream();
//			Path dirPath = path.resolve(fileDTO.getMultipartFile().getOriginalFilename());
//			Files.copy(inputStream, dirPath, StandardCopyOption.REPLACE_EXISTING);
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
		
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
	 * 
	 * 
	 */

	@Override
	public boolean deleteImageInDirectory(String originalName, String folderName) throws IOException {

		Resource resource = new FileSystemResource(Path.of(DEFAULT_PATH + folderName + File.separator + folderName));
		return resource.getFile().delete();
	}

//	@Override
//	public boolean existsImage(String originalName, String folderName) {
//		// TODO Auto-generated method stub
//		Resource resource = new FileSystemResource(Path.of(DEFAULT_PATH + folderName + File.separator + originalName));
//		return resource.exists();
//	}

}
