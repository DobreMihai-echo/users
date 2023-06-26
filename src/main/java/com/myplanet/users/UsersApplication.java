package com.myplanet.users;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
//@EnableFeignClients
public class UsersApplication {

	public static void main(String[] args) {
		System.out.println("LOOK HERE FOR THE ANSWER");
		SpringApplication.run(UsersApplication.class, args);
	}

}
