package com.indices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

import lombok.extern.slf4j.Slf4j;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(scanBasePackages = "com.indices")
@EnableSwagger2
@Slf4j
@EnableAsync
public class IndicesPresentationApiApplication {


	public static void main(String[] args) {
		SpringApplication.run(IndicesPresentationApiApplication.class, args);
	}

}
