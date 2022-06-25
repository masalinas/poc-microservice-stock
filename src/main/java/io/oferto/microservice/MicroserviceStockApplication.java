package io.oferto.microservice;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@EnableFeignClients("io.oferto.microservice.proxy")
@OpenAPIDefinition(info = @Info(title = "Stock API", version = "1.0", description = "Stock Microservice API Information"))
public class MicroserviceStockApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceStockApplication.class, args);
	}

    @Bean
    ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setAmbiguityIgnored(true);

        return modelMapper;
    }
}
