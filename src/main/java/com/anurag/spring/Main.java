package com.anurag.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.anurag.spring.*")
@EntityScan("com.anurag.spring.*")
public class Main {

    public static void main(String[] args) {

        SpringApplication.run(Main.class,args);

    }
}
