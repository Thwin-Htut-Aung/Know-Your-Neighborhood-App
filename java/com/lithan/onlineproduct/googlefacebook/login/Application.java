package com.lithan.onlineproduct.googlefacebook.login;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.lithan.onlineproduct.googlefacebook.login.configuration.AppProperties;

@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
//Enable AppPropterties Configuration class to use in TokenProvider Class
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
