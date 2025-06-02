package com.example.reply;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class ReplyApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReplyApplication.class, args);
	}

}
