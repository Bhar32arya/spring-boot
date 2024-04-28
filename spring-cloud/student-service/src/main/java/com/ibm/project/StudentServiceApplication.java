package com.ibm.project;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@EnableEurekaClient
@EnableFeignClients
@SpringBootApplication
public class StudentServiceApplication {

	
	@Value(value = "${address.service.url}")
	private String addressServiceUrl;
	
	public static void main(String[] args) {
		SpringApplication.run(StudentServiceApplication.class, args);
	}

    @Bean
    WebClient weblient() {
		return WebClient.builder().baseUrl(addressServiceUrl).build();
	}

}
