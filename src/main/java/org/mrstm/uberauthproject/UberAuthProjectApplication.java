package org.mrstm.uberauthproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing //for createdat nd updatedat working
public class UberAuthProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(UberAuthProjectApplication.class, args);
    }

}
