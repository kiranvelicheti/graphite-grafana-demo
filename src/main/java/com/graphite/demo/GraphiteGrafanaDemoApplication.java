package com.graphite.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableAutoConfiguration
@EnableSwagger2
@ComponentScan
public class GraphiteGrafanaDemoApplication{

	public static void main(String[] args) {
		SpringApplication.run(GraphiteGrafanaDemoApplication.class, args);
	}
}
