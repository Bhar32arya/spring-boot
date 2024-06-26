package com.ibm.project;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Configuration
public class CustomFilter implements GlobalFilter {

	private static final Logger logger = LoggerFactory.getLogger(CustomFilter.class);
	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		
		ServerHttpRequest httpRequest = exchange.getRequest();
		logger.info("Authorization " + httpRequest.getHeaders().getFirst("Authorization"));
		
		/*
		 * if(httpRequest.getURI().toString().contains("/api/student")) { //we can have
		 * our own implementation }
		 */
		
		return chain.filter(exchange).then(Mono.fromRunnable(() -> {
			
		ServerHttpResponse httpResponse = exchange.getResponse();
		logger.info("Post filter " + httpResponse.getStatusCode());
		}));
	}
	

}
