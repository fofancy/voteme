package com.fofancy.auth.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

@Component
public class TokenAuthenticationFailureHandler implements AuthenticationFailureHandler {
    private static Logger log = Logger.getLogger(UsernamePasswordLoginFilter.class.getName());

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        log.info("Authentication failed");

        response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
    }
}
