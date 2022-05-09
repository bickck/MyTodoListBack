package com.todo.list.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.todo.list.domain.UserEntity;
import com.todo.list.domain.UserQuoteEntity;

@EnableJpaRepositories
public interface UserRepository extends JpaRepository<UserEntity, Long> {

	UserEntity findByUsername(String username);

	UserEntity findByUsernameAndPassword(String username, String password);

	void deleteByUsernameAndPassword(String username, String password);

	@Query("SELECT u FROM USER_ENTITY u join fetch u.quotes")
	List<UserEntity> findAllbyfetchJoin();

//	@Query("SELECT u FROM USER_ENTITY u join fetch u.quotes")
//	List<UserEntity> findAll();
}
