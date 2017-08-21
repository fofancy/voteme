package com.fofancy.auth.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class TokenAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    TokenService tokenService;

    @Autowired
    ObjectMapper mapper;

    public TokenAuthenticationSuccessHandler() {
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Map<String, String> params = new HashMap<>();
        params.put("username", (String) principal);
        TokenDto token = tokenService.createToken(params);

        mapper.writeValue(response.getWriter(), token);

        response.setStatus(HttpServletResponse.SC_OK);
    }
}
