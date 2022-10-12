package com.todo.list.repository;

import java.util.List;

import org.hibernate.annotations.BatchSize;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.Async;

import com.todo.list.entity.UserEntity;
import com.todo.list.controller.dto.user.UserIntro;
import com.todo.list.controller.dto.user.UserIntroDTO;
import com.todo.list.entity.UserQuoteEntity;

//@EnableJpaRepositories
public interface UserRepository extends JpaRepository<UserEntity, Long> {

	UserEntity findByUsername(String username);

	UserEntity findByUsernameAndPassword(String username, String password);

	UserEntity findByEmail(String email);

	@Query("SELECT u FROM USER_ENTITY u join fetch u.quotes")
	List<UserEntity> findAllbyfetchJoin();

	@Query(name = "SELECT" + "u.id, u.username, u.introComment, i.fileName, i.location"
			+ "FROM USER_ENTITY u inner join USER_IMAGE i on u.id = i.id where=#{username}", nativeQuery = true)
	UserEntity findUserIntroInfoByUsername(String username);

	Page<UserEntity> findAll(Pageable pageable);

	void deleteByUsernameAndPassword(String username, String password);

	// long count();
}
