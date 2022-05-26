package com.todo.list.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.todo.list.controller.dto.ImageInfoDTO;
import com.todo.list.entity.base.AdminImageEntity;
import com.todo.list.repository.mapper.ImageInfoMapper;

public interface AdminImageRepository extends JpaRepository<AdminImageEntity, Long> {

	// List<ImageInfoDTO> findOriginalFileNameAndFilePath();
//	@Query(value = "SELECT i.originalFileName, i.filePath FROM ADMIN_IMAGE i")
//	List<ImageInfoMapper> findOriginalFileNameAndFilePatha();

//	List<ImageInfoMapper> findAll();

	@Query(value = "SELECT originalFileName, filePath FROM ADMIN_IMAGE ", nativeQuery = true)
	List<ImageInfoMapper> findOriginalFileNameAndFilePath();
	
	List<String> findOriginalFileName();

	AdminImageEntity findByOriginalFileName(String originalFilename);

	boolean existsByOriginalFileName(String originalFileName);

}
