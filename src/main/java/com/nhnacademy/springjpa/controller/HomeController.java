package com.nhnacademy.springjpa.controller;

import com.nhnacademy.springjpa.CookieUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Objects;

@Controller
public class HomeController {
    @GetMapping("/")
    public String home(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        if (!Objects.isNull(session)) {
            session.invalidate();
        }

        Cookie cookie = CookieUtils.getCookie(request, "SESSION");
        if (!Objects.isNull(cookie)) {
            cookie.setValue("");
            cookie.setMaxAge(0);
            response.addCookie(cookie);
        }
        return "home";
    }
}
