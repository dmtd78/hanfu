package com.sunwin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:dubbo-provider.xml")
public class DubboZkpProviderApplication {

	public static void main(String[] args) {
		SpringApplication.run(DubboZkpProviderApplication.class, args);
	}

}
