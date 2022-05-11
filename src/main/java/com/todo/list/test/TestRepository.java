package com.todo.list.test;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepository extends JpaRepository<TestEntity, Long>{

	boolean existsByTest(String test);
}
