package com.musketeers.classserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@ServletComponentScan
@EnableSwagger2
@EnableEurekaClient
public class ClassServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClassServerApplication.class, args);
	}

}
