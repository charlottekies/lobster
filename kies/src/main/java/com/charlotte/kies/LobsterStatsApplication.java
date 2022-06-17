package com.charlotte.kies;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchDataAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//@SpringBootApplication(exclude = ElasticsearchDataAutoConfiguration.class)
@SpringBootApplication
@ComponentScan({"com.charlotte.kies.controller", "com.charlotte.kies.repository", "com.charlotte.kies.service"})
@EnableJpaRepositories("com.charlotte.kies.repository")
@EntityScan(basePackages = {"com.charlotte.kies"})
public class LobsterStatsApplication {


	public static void main(String[] args) {
		SpringApplication.run(LobsterStatsApplication.class, args);
	}

}
