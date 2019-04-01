package com.example.basic_poc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing(auditorAwareRef = "springSecurityAuditorAware")
@SpringBootApplication

public class BasicPocApplication {

  public static void main(String[] args) {
    SpringApplication.run(BasicPocApplication.class, args);
  }

}
