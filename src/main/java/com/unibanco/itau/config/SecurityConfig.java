package com.unibanco.itau.config;

import com.unibanco.itau.entity.Users;
import com.unibanco.itau.repository.UsersRepository;
import com.unibanco.itau.service.JpaUserDetailService;
import com.unibanco.itau.service.JwtAuthenticationFilter;
import com.unibanco.itau.service.JwtService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, JwtAuthenticationFilter jwtFilter) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/login").permitAll()
                        .requestMatchers("/api/health").permitAll()
                        .requestMatchers("/api/transacao").authenticated()
                        .requestMatchers(HttpMethod.DELETE).hasAuthority("ADMIN")
                        .anyRequest().authenticated()
                )
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter(JwtService jwtService, JpaUserDetailService jpaUserDetailService){
        return new JwtAuthenticationFilter(jwtService,jpaUserDetailService);
    }

    @Bean
    public AuthenticationManager authenticationManager (
            AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public JpaUserDetailService jpaUserDetailService (UsersRepository usersRepository){
        return new JpaUserDetailService(usersRepository);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CommandLineRunner initDatabase(UsersRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if(userRepository.findByUsername("user").isEmpty()){
                var password = passwordEncoder.encode("123");
                var username = "user";
                var role = List.of("USER");
                Users user = new Users(username, password, role);
                userRepository.save(user);
            }
            if(userRepository.findByUsername("admin").isEmpty()){
                var password = passwordEncoder.encode("123");
                var username = "admin";
                var role = List.of("USER", "ADMIN");
                Users admin = new Users(username, password, role);
                userRepository.save(admin);
            }
        };
    }
}
