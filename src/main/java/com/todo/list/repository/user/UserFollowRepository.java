package com.todo.list.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;

import com.todo.list.entity.UserFollowEntity;

public interface UserFollowRepository extends JpaRepository<UserFollowEntity, Long> {

}
