package com.nhnacademy.springjpa.controller;

import com.nhnacademy.springjpa.entity.Resident;
import com.nhnacademy.springjpa.service.resident.CustomResidentDetailService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Controller
public class githubController {

    private final RestTemplate restTemplate;
    private final CustomResidentDetailService customResidentDetailService;

    public githubController(RestTemplate restTemplate, CustomResidentDetailService customResidentDetailService) {
        this.restTemplate = restTemplate;
        this.customResidentDetailService = customResidentDetailService;
    }

    @GetMapping("/login/github")
    public void responseId(HttpServletResponse response) throws IOException {
        String clientId = "5621754e6f5a1329b6e9";

        String uri = "https://github.com/login/oauth/authorize" +
                "?response_type=code" +
                "&state=" + "1234" +
                "&client_id=" + clientId;
        response.sendRedirect(uri);
    }

    @GetMapping("/login/oauth2/code/github")
    public String responseCode(HttpServletRequest request,
                               HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);

        String clientId = "5621754e6f5a1329b6e9";
        String clientSecret = "34be43e77159a15e2606c04a985e431502fcb4fd";
        String code = request.getParameter("code");

        UriComponents uriComponents = UriComponentsBuilder.newInstance()
                .scheme("https")
                .host("github.com")
                .path("/login/oauth/access_token")
                .queryParam("client_id", clientId)
                .queryParam("client_secret", clientSecret)
                .queryParam("code", code)
                .build();

        HashMap tokenMap = restTemplate.postForObject(uriComponents.toUri(), null, HashMap.class);

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth((String) tokenMap.get("access_token"));
        HttpEntity httpEntity = new HttpEntity(headers);

        ResponseEntity<HashMap> exchange = restTemplate.exchange("https://api.github.com/user", HttpMethod.GET, httpEntity, HashMap.class);

        String email = (String) exchange.getBody().get("email");

        Resident resident = customResidentDetailService.findByEmail(email);
        session.setAttribute("id", resident.getId());
        User user = new User(resident.getId(), resident.getPassword(), Collections.singletonList(new SimpleGrantedAuthority("resident")));

        Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        SecurityContext context = SecurityContextHolder.getContext();
        context.setAuthentication(authentication);
        return "home";
    }
}
