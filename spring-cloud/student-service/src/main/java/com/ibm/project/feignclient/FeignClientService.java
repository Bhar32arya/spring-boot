package com.ibm.project.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.ibm.project.request.AddressResponse;

@FeignClient(value = "spring-cloud-gateway")
public interface FeignClientService {

	@GetMapping("/address-service/api/address/getById/{id}")
	public AddressResponse getById(@PathVariable long id);

}
