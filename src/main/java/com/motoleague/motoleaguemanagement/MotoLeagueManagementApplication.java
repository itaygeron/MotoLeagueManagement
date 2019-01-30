package com.motoleague.motoleaguemanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan("com.motoleague.*")
@EntityScan("com.motoleague.*")
@EnableJpaRepositories("com.motoleague.*")
public class MotoLeagueManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(MotoLeagueManagementApplication.class, args);
	}
}
