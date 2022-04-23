package com.todo.list.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.todo.list.domain.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

	UserEntity findByUsername(String username);

	UserEntity findByUsernameAndPassword(String username, String password);

	void deleteByUsernameAndPassword(String username, String password);
}
