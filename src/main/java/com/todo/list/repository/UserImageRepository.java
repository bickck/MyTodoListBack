package com.todo.list.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.todo.list.entity.BackGroundImageEntity;

public interface UserImageRepository extends JpaRepository<BackGroundImageEntity, Long> {

}
