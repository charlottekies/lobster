package com.charlotte.kies;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchDataAutoConfiguration;

@SpringBootApplication(exclude = ElasticsearchDataAutoConfiguration.class)
public class LobsterStatsApplication {

	public static void main(String[] args) {
		SpringApplication.run(LobsterStatsApplication.class, args);
	}

}
