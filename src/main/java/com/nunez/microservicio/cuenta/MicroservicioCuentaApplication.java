package com.nunez.microservicio.cuenta;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

import org.springdoc.core.annotations.RouterOperation;
import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Configuration
public class MicroservicioCuentaApplication {
	
	  @Bean
	    public OpenAPI customOpenAPI() {
	        return new OpenAPI()
	                .info(new Info()
	                        .title("Microservicio de Cuentas")
	                        .version("1.0.0")
	                        .description("Este microservicio gestiona las operaciones relacionadas con las cuentas, como la creación, consulta, realizar depósito, realizar retiro."));
	    }

	public static void main(String[] args) {
		SpringApplication.run(MicroservicioCuentaApplication.class, args);
	}

}
