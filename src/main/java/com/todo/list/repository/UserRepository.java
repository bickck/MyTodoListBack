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
import com.todo.list.entity.UserQuoteEntity;

@EnableJpaRepositories
public interface UserRepository extends JpaRepository<UserEntity, Long> {

	UserEntity findByUsername(String username);

	UserEntity findByUsernameAndPassword(String username, String password);

	void deleteByUsernameAndPassword(String username, String password);

	List<UserQuoteEntity> findQuoteEntitiesByUsername(String username);

	Page<UserEntity> findAll(Pageable pageable);

	@Query("SELECT u FROM USER_ENTITY u join fetch u.quotes")
	List<UserEntity> findAllbyfetchJoin();

	List<UserEntity> findAll();
}
