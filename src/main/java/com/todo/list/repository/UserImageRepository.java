package com.todo.list.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.todo.list.domain.UserBackGroundImageEntity;

public interface UserImageRepository extends JpaRepository<UserBackGroundImageEntity, Long> {

}
