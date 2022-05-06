package com.todo.list.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.todo.list.domain.base.DefaultImageEntity;

public interface DefaultImageRepository extends JpaRepository<DefaultImageEntity, Long> {

}
