package com.todo.list;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;


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
