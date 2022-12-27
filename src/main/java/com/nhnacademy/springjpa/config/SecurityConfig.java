package com.nhnacademy.springjpa.config;

import com.nhnacademy.springjpa.auth.CustomLogoutSuccessHandler;
import com.nhnacademy.springjpa.auth.LoginSuccessHandler;
import com.nhnacademy.springjpa.service.resident.CustomResidentDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

@EnableWebSecurity(debug = true)
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        return http.authorizeRequests()
                    .antMatchers("/family/**").authenticated()
                    .anyRequest().permitAll()
                    .and()
                .formLogin()
                    .usernameParameter("id")
                    .passwordParameter("pwd")
                    .loginPage("/auth/login")
                    .loginProcessingUrl("/login")
                    .successHandler(loginSuccessHandler())
                    .and()
                .logout()
                    .deleteCookies("SESSEION")
                    .invalidateHttpSession(true)
                    .logoutSuccessHandler(logoutSuccessHandler(null))
                    .and()
                .csrf()
//                    .disable()
                    .and()
                    .sessionManagement()
                        .sessionFixation()  //로그인을 하면 세션이 바뀌는데 이때 로그인 할때마다 세션의 아이디를 바꾸는 설정
                            .none()
                        .and()
                    .headers()
                        .defaultsDisabled()
                        .frameOptions() //같은 도메인이면 프레임받는걸 허용
                        .sameOrigin()
                        .and()
                .exceptionHandling()
                    .accessDeniedPage("/error/403") //접근이 제한된 페이지 보여주기
                    .and()
                    .build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(CustomResidentDetailService customResidentDetailService) {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(customResidentDetailService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());

        return authenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationSuccessHandler loginSuccessHandler() {
        return new LoginSuccessHandler();
    }

    @Bean
    public LogoutSuccessHandler logoutSuccessHandler(RedisTemplate<String, Object> redisTemplate) {
        return new CustomLogoutSuccessHandler(redisTemplate);
    }
}
