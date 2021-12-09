package com.csiris.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class ReactiveSseStreamsReactJsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReactiveSseStreamsReactJsApplication.class, args);
	}

}
