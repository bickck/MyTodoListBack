package com.todo.list.repository;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;

import com.todo.list.controller.dto.ImageInfoDTO;
import com.todo.list.entity.base.DefaultImageEntity;
import com.todo.list.repository.mapper.ImageInfoMapper;

public interface DefaultImageRepository extends JpaRepository<DefaultImageEntity, Long> {

	//List<ImageInfoDTO> findOriginalFileNameAndFilePath();
	
	//@Query(value = "SELECT i.originalFilename, i.filePath FROM defaultImage i")
//	List<DefaultIma> findOriginalFileNameAndFilePath();
}
