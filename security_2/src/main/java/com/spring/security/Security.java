package com.spring.security;
//
import org.springframework.context.annotation.Bean;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//
@Configuration
//@EnableMethodSecurity
public class Security {

    @Bean
    public ModelMapper passwordEncoder(){
        return new ModelMapper();
    }
//
//    String[] arg = {"/home" , "/home/public"};
//
//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public UserDetailsService userDetailsService (){
//        UserDetails adminUser = User
//                .builder()
//                .username("admin")
//                .password(passwordEncoder().encode("admin"))
//                .roles("ADMIN")
//                .build();
//
//        UserDetails normalUser = User
//                .builder()
//                .username("normal")
//                .password(passwordEncoder().encode("normal"))
//                .roles("NORMAL")
//                .build();
//
//
//        return new InMemoryUserDetailsManager(normalUser , adminUser);
//    }
//
//
//    @Bean
//    public UserDetailsService getUserDetailsService() {
//
//        return new CustomUserDetailsService();
//    }
//
//    @Bean
//    public DaoAuthenticationProvider daoAuthenticationProvider () {
//
//        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
//        daoAuthenticationProvider.setUserDetailsService(this.getUserDetailsService());
//        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
//
//        return daoAuthenticationProvider;
//    }
//
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity.csrf(AbstractHttpConfigurer::disable)
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/home/admin")
//                        .hasRole("ADMIN")
//                        .requestMatchers("/home/normal")
//                        .hasRole("NORMAL")
//                        .requestMatchers(arg).permitAll()
//                        .anyRequest().authenticated());
//        httpSecurity.formLogin(Customizer.withDefaults());
//        return httpSecurity.build();
    }
//
//}
