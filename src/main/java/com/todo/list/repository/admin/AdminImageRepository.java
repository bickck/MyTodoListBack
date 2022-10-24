package com.todo.list.repository.admin;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.todo.list.entity.base.AdminImageEntity;
import com.todo.list.repository.mapper.ImageMapper;

public interface AdminImageRepository extends JpaRepository<AdminImageEntity, Long> {

	@Query(value = "SELECT originalFileName, filePath FROM ADMIN_IMAGE ", nativeQuery = true)
	List<ImageMapper> findOriginalFileNameAndFilePath();

	AdminImageEntity findByOriginalFileName(String originalFilename);

	boolean existsByOriginalFileName(String originalFileName);

}
