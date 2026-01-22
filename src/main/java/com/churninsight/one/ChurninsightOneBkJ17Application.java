package com.churninsight.one;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

@SpringBootApplication
public class ChurninsightOneBkJ17Application {

	public static void main(String[] args) {
		SpringApplication.run(ChurninsightOneBkJ17Application.class, args);
	}

	@Bean
	public DataSource dataSource() {
		return DataSourceBuilder.create()
				.driverClassName("org.h2.Driver")
				.url("jdbc:h2:mem:churninsight_poc_mvp_db;DB_CLOSE_DELAY=-1")
				.username("sa")
				.password("")
				.build();
	}


}
