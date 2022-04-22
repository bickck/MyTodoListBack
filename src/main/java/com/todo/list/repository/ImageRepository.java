package com.todo.list.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.todo.list.domain.base.ImageEntity;

public interface ImageRepository extends JpaRepository<ImageEntity, Long> {

}
