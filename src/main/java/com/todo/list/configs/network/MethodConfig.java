package com.todo.list.configs.network;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@Configuration
//@EnableWebMvc
public class MethodConfig implements WebMvcConfigurer{

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		// TODO Auto-generated method stub

		registry.addMapping("/**")
				.allowCredentials(false)
				//.allowedOriginPatterns("http://127.0.0.1:5500/user/*,http://127.0.0.1:5500/auth/* ")
				.allowedOrigins("*")
				.allowedHeaders("authorization")
				.allowedMethods(
						HttpMethod.OPTIONS.name(),
						HttpMethod.GET.name(),
						HttpMethod.POST.name(),
						HttpMethod.PATCH.name()
				)
				.maxAge(3600);
	}
}
