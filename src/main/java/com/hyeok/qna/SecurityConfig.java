package com.hyeok.qna;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        //인가
        http.authorizeHttpRequests((auth) -> auth
                .requestMatchers("/question/list", "/question/all").hasRole(Role.ADMIN.name())
                .anyRequest().permitAll()
        );
        // 로그인 설정
        http
                .formLogin((auth) -> auth
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/")
                );
        // 로그아웃 URL 설정
        http
                .logout((auth) -> auth
                        .logoutUrl("/logout")
                );
        // 개발단계에서만 csrf 잠시 꺼두기
        http
                .csrf((auth) -> auth.disable());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
