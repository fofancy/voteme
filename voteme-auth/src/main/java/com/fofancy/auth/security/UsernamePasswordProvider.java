package com.fofancy.auth.security;

import com.fofancy.auth.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class UsernamePasswordProvider implements AuthenticationProvider {
    @Autowired
    private BCryptPasswordEncoder encoder;
    @Autowired
    private UserService loginService;

    private static Logger log = Logger.getLogger(UsernamePasswordProvider.class.getName());

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        log.info("UsernamePasswordProvider invocation");

        String username = (String)authentication.getPrincipal();
        String password = (String) authentication.getCredentials();

        UserDetails userDetails = loginService.loadUserByUsername(username);

        if(userDetails == null)
            throw new UsernameNotFoundException("Username not found: " + username);

        if(!encoder.matches(password, userDetails.getPassword()))
            throw new BadCredentialsException("Authentication failed. Username or Password not valid");

        return new UsernamePasswordAuthenticationToken(
                userDetails.getUsername(),
                null,
                userDetails.getAuthorities()
        );
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
