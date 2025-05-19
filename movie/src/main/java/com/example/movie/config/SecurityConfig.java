package com.example.movie.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices.RememberMeTokenAlgorithm;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@Configuration
public class SecurityConfig {
    // security password 검색 하면 user 계정의 기본 비번 얻을 수 있음
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http, RememberMeServices rememberMeServices) throws Exception {
        http.authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/", "assets/**", "/css/**", "/js/**", "/upload/**").permitAll()
                .requestMatchers("/movie/list", "/movie/read").permitAll()
                .requestMatchers("/reviews/**", "/upload/display/**").permitAll()
                .requestMatchers("/member/register").permitAll()
                .anyRequest().permitAll());
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS));
        // http.csrf(csrf -> csrf.disable());
        http.formLogin(login -> login.loginPage("/member/login").defaultSuccessUrl("/movie/list").permitAll()) // .successHandler(successHandler()).permitAll())
                .logout(logout -> logout.logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
                        .logoutSuccessUrl("/"));
        // http.rememberMe(remember -> remember.rememberMeServices(rememberMeServices));
        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    // @Bean
    // CustomLoginSuccessHandler successHandler() {
    // return new CustomLoginSuccessHandler();
    // }

    @Bean
    RememberMeServices rememberMeServices(UserDetailsService userDetailsService) {
        RememberMeTokenAlgorithm encodingAlgorithm = RememberMeTokenAlgorithm.SHA256;
        TokenBasedRememberMeServices rememberMeServices = new TokenBasedRememberMeServices("myKey", userDetailsService,
                encodingAlgorithm);
        rememberMeServices.setMatchingAlgorithm(RememberMeTokenAlgorithm.MD5);
        rememberMeServices.setTokenValiditySeconds(60 * 60 * 24 * 7);
        return rememberMeServices;
    }
}
