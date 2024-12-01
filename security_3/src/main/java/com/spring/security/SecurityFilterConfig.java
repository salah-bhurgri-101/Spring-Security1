package com.spring.security;

import com.spring.security.jwt.JWTAuthenticationFilter;
import com.spring.security.jwt.JwtAuthenticationEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityFilterConfig {

    String[] arg = {"/user/**" , "/home/public"};

    @Autowired
    private JwtAuthenticationEntryPoint point;

    @Autowired
    private JWTAuthenticationFilter filter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity security) throws Exception {
        return security.csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth->auth
//                        .requestMatchers("/home/admin","/home/normal")
                        .requestMatchers("/home/admin")
                        .hasRole("ADMIN")
                        .requestMatchers("/home/normal")
                        .hasRole("NORMAL")
                        .requestMatchers(arg)
                        .permitAll()
                        .anyRequest().authenticated())
                .exceptionHandling(ex ->ex.authenticationEntryPoint(point))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
                .build();

    }
}
