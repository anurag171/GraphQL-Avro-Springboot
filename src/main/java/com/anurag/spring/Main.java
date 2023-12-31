package com.anurag.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableJpaRepositories("com.anurag.spring.*")
@EntityScan("com.anurag.spring.*")
@EnableScheduling
@EnableJpaAuditing
public class Main {

    public static void main(String[] args) {

        SpringApplication.run(Main.class,args);

    }
}
