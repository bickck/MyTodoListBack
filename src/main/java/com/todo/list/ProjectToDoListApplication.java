package com.todo.list;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.event.EventListener;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.todo.list.entity.base.AdminQuoteEntity;
import com.todo.list.repository.admin.AdminQuoteRepository;

/**
 * 저장 수정 삭제는 *Controller
 * 
 * 조회는 ApiController
 * 
 * @author 3d193
 *
 */

@ServletComponentScan
@EnableAspectJAutoProxy
@SpringBootApplication
//@EnableJpaRepositories(basePackages = "com.todo.list.repository.custom", repositoryImplementationPostfix = "CustomeImpl")
public class ProjectToDoListApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectToDoListApplication.class, args);

	}

}
