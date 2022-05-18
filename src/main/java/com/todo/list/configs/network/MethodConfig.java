package com.todo.list.configs.network;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@Configuration
public class MethodConfig {

	@Bean
	public WebMvcConfigurer configurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				// TODO Auto-generated method stub
				registry.addMapping("/**")
				.allowCredentials(false)
				.allowedMethods(
						HttpMethod.PATCH.name(),
						HttpMethod.HEAD.name(),
						HttpMethod.TRACE.name(),
						HttpMethod.PUT.name(),
						HttpMethod.OPTIONS.name());

			}
		};
	}
	
	@Bean
	public WebMvcConfigurer mvcConfigurer() {
		return new WebMvcConfigurer() {
			
		}; 
	}
}
