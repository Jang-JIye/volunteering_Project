package com.team8.volunteerworkproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class VolunteerWorkProjectApplication {

  public static void main(String[] args) {
    SpringApplication.run(VolunteerWorkProjectApplication.class, args);
  }

}
