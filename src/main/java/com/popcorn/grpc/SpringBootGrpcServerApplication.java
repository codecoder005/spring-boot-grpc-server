package com.popcorn.grpc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SpringBootGrpcServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootGrpcServerApplication.class, args);
	}

}
