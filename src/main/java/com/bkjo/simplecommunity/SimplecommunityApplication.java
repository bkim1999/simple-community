package com.bkjo.simplecommunity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import jakarta.persistence.EntityListeners;

@SpringBootApplication
@EnableJpaAuditing
public class SimplecommunityApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimplecommunityApplication.class, args);
	}

}
