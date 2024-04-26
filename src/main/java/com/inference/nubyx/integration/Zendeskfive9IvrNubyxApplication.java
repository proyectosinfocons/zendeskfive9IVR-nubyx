package com.inference.nubyx.integration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@EnableFeignClients
@SpringBootApplication
public class Zendeskfive9IvrNubyxApplication {

	public static void main(String[] args) {
		SpringApplication.run(Zendeskfive9IvrNubyxApplication.class, args);
	}

}
