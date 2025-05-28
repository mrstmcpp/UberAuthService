package org.mrstm.uberauthproject.configurations;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SpringSecurity {
    //for setting up spring security.
    //this also helps to avoid authentication for first time registration.
    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) // for avoiding 403 on sending requests..
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                        .requestMatchers("/api/v1/auth/signup/**").permitAll()
                        .anyRequest().permitAll()
                )
        ;
        return http.build();
    }

    @Bean //telling spring boot that it is a bean
    // as there could 6 type of more constructors could be possible we need to define which one to use.
    // u can check it in the BCryptPasswordEncoder package.
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
