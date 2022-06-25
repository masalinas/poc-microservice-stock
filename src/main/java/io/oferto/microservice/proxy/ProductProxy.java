package io.oferto.microservice.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import io.oferto.microservice.common.ProductDto;

@FeignClient(name = "product-service", url = "${microservice.recomendation.url}")
public interface ProductProxy {
	@RequestMapping(value = "/api/products/{id}", method = RequestMethod.GET)	
	public ProductDto getProduct(@PathVariable("id") Long id);
}
