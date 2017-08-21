package com.fofancy.auth.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

public class UnauthorizedEntryPoint implements AuthenticationEntryPoint {
    private static Logger log = Logger.getLogger(UsernamePasswordLoginFilter.class.getName());

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        log.info("Authentication failed");

        response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
    }
}
