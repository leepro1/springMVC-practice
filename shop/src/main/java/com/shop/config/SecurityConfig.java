package com.shop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authorizeHttpRequests) ->
                        authorizeHttpRequests
                                .requestMatchers(new AntPathRequestMatcher("/**")).permitAll()
                                .anyRequest().authenticated())
                .csrf((csrf) ->
                        csrf
                                .ignoringRequestMatchers(new AntPathRequestMatcher("/h2-console/**")))
                .headers((headers) -> headers
                        .addHeaderWriter(new XFrameOptionsHeaderWriter(
                                XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN)))
                .formLogin((formLogin) ->
                        formLogin
                                .loginPage("/member/login") //로그인 페이지
                                .defaultSuccessUrl("/") //로그인 성공시 이동할 URL
                                .usernameParameter("email") //로그인 시 사용할 파라미터 이름
                                .failureUrl("/member/login/error")) //로그인 실패시 이동할 URL
                .logout((logout) ->
                        logout
                                .logoutRequestMatcher(new AntPathRequestMatcher("/member/logout")) //로그아웃 페이지
                                .logoutSuccessUrl("/")) //로그아웃 성공시 이동할 URL
        ;
        return http.build();
    }

    //패스워드 인코더로 사용할 빈 등록
    @Bean
    public PasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration auth)
            throws Exception {
        return auth.getAuthenticationManager();
    }
}
