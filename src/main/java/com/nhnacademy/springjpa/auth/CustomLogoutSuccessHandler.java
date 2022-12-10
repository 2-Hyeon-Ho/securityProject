package com.nhnacademy.springjpa.auth;

import com.nhnacademy.springjpa.CookieUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {
    private final RedisTemplate<String, Object> redisTemplate;
    private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();    //스프링 시큐리티에서 제공하는 리다이렉트

    public CustomLogoutSuccessHandler(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String sessionId = CookieUtils.getCookie(request, "SESSION");
        if (Objects.nonNull(sessionId)) {
            redisTemplate.delete(sessionId);
        }

        redirectStrategy.sendRedirect(request, response, "/auth/login");
    }
}
