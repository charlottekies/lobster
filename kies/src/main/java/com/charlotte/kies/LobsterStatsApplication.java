package com.charlotte.kies;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchDataAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.Arrays;
import java.util.Collections;

//@SpringBootApplication(exclude = ElasticsearchDataAutoConfiguration.class)
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class }) // disables security login
@ComponentScan({"com.charlotte.kies", "com.charlotte.kies.controller", "com.charlotte.kies.repository", "com.charlotte.kies.service", "com.charlotte.kies.model"})
//@EnableJpaRepositories("com.charlotte.kies.repository")
//@EntityScan(basePackages = {"com.charlotte.kies.model"})
public class LobsterStatsApplication {

	public static void main(String[] args) {
//		System.setProperty("server.servlet.context-path", "/api");
		SpringApplication.run(LobsterStatsApplication.class, args);


	}
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("http://localhost:3000");
			}
		};
	}


}
