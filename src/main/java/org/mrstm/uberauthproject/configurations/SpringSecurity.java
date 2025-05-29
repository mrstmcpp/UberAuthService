package org.mrstm.uberauthproject.configurations;


import org.mrstm.uberauthproject.services.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SpringSecurity {
    //for setting up spring security.
    //this also helps to avoid authentication for first time registration.

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }



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



    //these below two methods are written to call spring security password matching mechanism.
    @Bean
    public AuthenticationProvider authenticationProvider() { //an AuthenticationProvider processes an Authentication request, and a fully authenticated object with full credentials is returned.
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(bCryptPasswordEncoder());
        return authProvider;
    }
    //this function is written for calling authenticationprovider.
    @Bean
    public AuthenticationManager authManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
