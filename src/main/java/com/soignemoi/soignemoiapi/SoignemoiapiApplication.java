package com.soignemoi.soignemoiapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.soignemoi.soignemoiapi.data.models")
public class SoignemoiapiApplication {

	public static void main(String[] args) { SpringApplication.run(SoignemoiapiApplication.class, args); }

}
