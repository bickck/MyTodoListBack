package com.todo.list.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.todo.list.entity.base.DefaultImageEntity;

public interface DefaultImageRepository extends JpaRepository<DefaultImageEntity, Long> {

}
