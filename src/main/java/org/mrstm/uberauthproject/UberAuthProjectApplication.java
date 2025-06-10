package org.mrstm.uberauthproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing //for createdat nd updatedat working
@EntityScan("org.mrstm.uberentityservice.models") // if using an external library we must scan for entities else it will treat them as a normal class
@EnableDiscoveryClient
public class UberAuthProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(UberAuthProjectApplication.class, args);
    }

}
