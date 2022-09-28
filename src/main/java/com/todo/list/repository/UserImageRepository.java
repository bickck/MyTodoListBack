package com.todo.list.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.todo.list.entity.UserImageEntity;

public interface UserImageRepository extends JpaRepository<UserImageEntity, Long> {

}
