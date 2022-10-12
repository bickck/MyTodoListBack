package com.todo.list.service.image;

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

import com.todo.list.controller.dto.service.FileDTO;

@Service
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
	public boolean deleteBackGroundImageInDir(String originalName, String folderName) throws IOException {

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
