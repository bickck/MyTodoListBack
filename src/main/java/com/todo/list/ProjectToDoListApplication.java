package com.todo.list;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.event.EventListener;

import com.todo.list.domain.base.DefaultQuoteEntity;
import com.todo.list.repository.DefaultQuoteRepository;

@ServletComponentScan
@EnableAspectJAutoProxy
@SpringBootApplication
public class ProjectToDoListApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectToDoListApplication.class, args);

	}

}
