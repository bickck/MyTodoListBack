package com.todo.list.service.image.physical;

import com.todo.list.controller.dto.ImageDTO;
import com.todo.list.file.FileSupportImpl;
import com.todo.list.util.uuid.CommonUUID;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Component
public class ImageUploaderImpl implements ImageUploader {

    @Value(value = "${image.path.user}")
    private String physicalAddress;

    @Override
    public ImageDTO saveImageInDir(MultipartFile multipartFile) {

        String originalFileName = multipartFile.getOriginalFilename();
        String fileName = new CommonUUID().generatorImageUUID();
        String filePath = new FileSupportImpl().generatorFilePath(fileName, physicalAddress);
        Long fileSize = multipartFile.getSize();

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
            InputStream inputStream = multipartFile.getInputStream();
            Path dirPath = path.resolve(originalFileName);
            Files.copy(inputStream, dirPath, StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        return new ImageDTO(fileName, originalFileName, filePath, fileSize);
    }

    @Override
    public Resource findImageInDirectory(String originalName, String folderName) {

        String path = folderName + File.separator + originalName;

        return new FileSystemResource(path);
    }

    @Override
    public boolean deleteImageInDirectory(String originalName, String folderName) throws Exception {

        Resource resource = new FileSystemResource(Path.of(physicalAddress + folderName + File.separator + folderName));

        try {

        } catch (Exception e) {
            // TODO: handle exception
            throw new Exception();
        }

        return resource.getFile().delete();
    }
}
