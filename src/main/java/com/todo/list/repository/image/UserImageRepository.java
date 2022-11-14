package com.todo.list.repository.image;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.todo.list.entity.UserImageEntity;
import com.todo.list.repository.mapper.ImageMapper;

public interface UserImageRepository extends JpaRepository<UserImageEntity, Long> {

	@Query(value="SELECT i.id AS id, i.originalFileName AS originalFileName, i.fileName AS fileName, i.filePath AS filePath "
			+ "FROM USER_IMAGE_ENTITY i WHERE i.id = :id")
	ImageMapper findUserIntroImageById(Long id);
}
