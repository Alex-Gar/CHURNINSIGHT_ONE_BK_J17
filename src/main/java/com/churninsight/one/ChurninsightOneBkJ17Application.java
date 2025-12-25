package com.churninsight.one;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ChurninsightOneBkJ17Application {

	public static void main(String[] args) {
		SpringApplication.run(ChurninsightOneBkJ17Application.class, args);
	}

}
