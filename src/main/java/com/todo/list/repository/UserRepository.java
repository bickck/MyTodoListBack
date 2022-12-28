package com.todo.list.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.todo.list.entity.UserEntity;
import com.todo.list.repository.mapper.UserIntroMapper;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

	UserEntity findByUsername(String username);

	UserEntity findByUsernameAndPassword(String username, String password);

	Optional<UserEntity> findByEmail(String email);

	@Query("SELECT u FROM USER_ENTITY u join fetch u.quotes")
	List<UserEntity> findAllbyfetchJoin();

	boolean existsByEmail(String email);

	Page<UserEntity> findAll(Pageable pageable);

	@Query(value = "SELECT u.USER_ID AS id, u.USERNAME AS username, u.INTRO_COMMENT AS introComment, "
			+ "i.FILENAME AS fileName, i.FILEPATH AS filePath , i.ORIGINALFILENAME AS originalFileName "
			+ "FROM USER_ENTITY AS u " 
			+ "LEFT JOIN USER_IMAGE_ENTITY AS i ON u.USER_ID = i.USER_ID "
			+ "WHERE u.USER_ID = :id and u.EMAIL= :email ", nativeQuery = true)
	UserIntroMapper findUserIntroInfoByIdAndEmail(@Param(value = "id") Long id, @Param(value = "email") String email);
	
	@Query(value = 
			"SELECT u.USER_ID AS id, u.USERNAME AS username, u.INTRO_COMMENT AS introComment, "
			+ "i.FILENAME AS fileName, i.FILEPATH AS filePath , i.ORIGINALFILENAME AS originalFileName "
			+ "FROM USER_ENTITY AS u " 
			+ "LEFT JOIN USER_IMAGE_ENTITY AS i ON u.USER_ID = i.USER_ID "
			+ "WHERE u.USERNAME = :username ", nativeQuery = true)
	UserIntroMapper findUserIntroInfoByUsername(@Param(value = "username") String id);

	// void deleteByIdAndUsernameAndPassword(Long id, String username, String
	// password);

}
