package com.ibm.project.loadbalancer;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.context.annotation.Bean;
import feign.Feign;

//Load balancer code is not required as gateway is Spring-cloud gateway is doing the balancing work.

 @LoadBalancerClient(value = "address-service")
public class AddSerLoadBalancer {
	
	@LoadBalanced
	@Bean
	Feign.Builder feigBuilder() {
		return Feign.builder();
	}

}

