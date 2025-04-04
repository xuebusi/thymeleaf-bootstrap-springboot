package com.example.thymeleaf;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.thymeleaf.mapper")
public class ThymeleafBootstrapSpringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(ThymeleafBootstrapSpringbootApplication.class, args);
	}

}
