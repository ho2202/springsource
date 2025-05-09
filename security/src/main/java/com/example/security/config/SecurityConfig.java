package com.example.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@Configuration
public class SecurityConfig {
    // security password 검색 하면 user 계정의 기본 비번 얻을 수 있음
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/", "/sample/guest").permitAll()
                .requestMatchers("/sample/member").hasRole("USER")
                .requestMatchers("/sample/admin").hasRole("ADMIN")
                .anyRequest().authenticated())
                // .httpBasic(Customizer.withDefaults());
                // .formLogin(Customizer.withDefaults()); // 제공되는 기본 로그인 폼 페이지
                .formLogin(login -> login.loginPage("/member/login").permitAll())
                .logout(logout -> logout.logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
                        .logoutSuccessUrl("/"));

        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    // @Bean
    // UserDetailsService users() { // user 생성
    // UserDetails user = User.builder().username("user")
    // .password("{bcrypt}$2a$10$fhxylEHHL0O8vxVBoCMofecuHRLd53w72S/vTHiyq4o8Cc//A19Te")
    // .roles("USER") // Granted Authorities=[ROLE_USER]]]
    // .build();
    // UserDetails admin = User.builder().username("admin")
    // .password("{bcrypt}$2a$10$fhxylEHHL0O8vxVBoCMofecuHRLd53w72S/vTHiyq4o8Cc//A19Te")
    // .roles("USER", "ADMIN") // Granted Authorities=[ROLE_USER]]]
    // .build();
    // return new InMemoryUserDetailsManager(user, admin);
    // }
}
