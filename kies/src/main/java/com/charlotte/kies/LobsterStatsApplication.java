package com.charlotte.kies;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchDataAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;

//@SpringBootApplication(exclude = ElasticsearchDataAutoConfiguration.class)
@SpringBootApplication
@EntityScan
public class LobsterStatsApplication {

	public static void main(String[] args) {
		SpringApplication.run(LobsterStatsApplication.class, args);
	}

}
