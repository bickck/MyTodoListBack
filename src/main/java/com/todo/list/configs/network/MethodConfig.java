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

//	@Bean
//	public WebMvcConfigurer configurer() {
//		return new WebMvcConfigurer() {
//			@Override
//			public void addCorsMappings(CorsRegistry registry) {
//				// TODO Auto-generated method stub
//				registry.addMapping("/**").allowCredentials(false).allowedOriginPatterns("http://localhost:5500")
//						.exposedHeaders("Authorization").allowedMethods(HttpMethod.PATCH.name(), HttpMethod.HEAD.name(),
//								HttpMethod.TRACE.name(), HttpMethod.PUT.name(), HttpMethod.OPTIONS.name());
//
//			}
//		};
//	}
	
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
	
	

//	@Bean
//	public CorsConfigurationSource configurationSource() {
//		long maxAge = 3600;
//		CorsConfiguration configuration = new CorsConfiguration();
//		configuration.setAllowedOrigins(Arrays.asList("http://127.0.0.1:5500"));
//		configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
//		configuration.setMaxAge(maxAge);
//		configuration.setAllowedHeaders(Arrays.asList("Authorization", "content-type", "x-auth-token"));
//		configuration.setExposedHeaders(Arrays.asList("x-auth-token"));
//		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//		source.registerCorsConfiguration("/user", configuration);
//		return source;
//	}

//	@Bean
//	public WebMvcConfigurer mvcConfigurer() {
//		return new WebMvcConfigurer() {
//
//		};
//	}
}
