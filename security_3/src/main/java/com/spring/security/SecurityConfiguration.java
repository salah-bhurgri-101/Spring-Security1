package com.spring.security;

import org.springframework.context.annotation.Bean;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

//    @Autowired
//    private JwtAuthenticationEntryPoint point;
//    @Autowired
//    private JWTAuthenticationFilter filter;



//    String[] arg = {"/user" , "/home/public"};

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(){
        return new CustomUserDetailService();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider () {

        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(this.userDetailsService());
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());

        return daoAuthenticationProvider;
    }
// why we not use here because of circuler dependenvy of security that why we  us this method in SecurityFilterConfig class
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity.csrf(AbstractHttpConfigurer::disable)
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/home/admin")
//                        .hasRole("ADMIN")
//                        .requestMatchers("/home/normal")
//                        .hasRole("NORMAL")
//                        .requestMatchers(arg).permitAll()
//                        .anyRequest().authenticated()).httpBasic(Customizer.withDefaults());
//        httpSecurity.formLogin(Customizer.withDefaults());
//        return httpSecurity.build();
//    }

//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity security) throws Exception {
//        return security.csrf(AbstractHttpConfigurer::disable)
//                .cors(AbstractHttpConfigurer::disable)
//                .authorizeHttpRequests(auth->auth
//                        .requestMatchers("/authenticate","/signup")
//                        .permitAll()
//                        .anyRequest().authenticated())
//                .exceptionHandling(ex ->ex.authenticationEntryPoint(point))
//                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
//                .build();
//
//    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }



}
