package com.fofancy.auth.security;

import com.fofancy.auth.model.TokenEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.logging.Logger;

@Component
public class TokenAuthenticationProvider implements AuthenticationProvider {

    private static Logger log = Logger.getLogger(TokenAuthenticationProvider.class.getName());

    @Autowired
    TokenService tokenService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        log.info("Token authentication provider invocation;");
        AuthenticationToken token = (AuthenticationToken) authentication;

        List<SimpleGrantedAuthority> grantedAuthorities = tokenService.getGrantedAuthoritiesByValue((String) token.getPrincipal());

        AuthenticationToken tokenWithAuthorities = new AuthenticationToken((String) token.getPrincipal(), grantedAuthorities);

        tokenWithAuthorities.setAuthenticated(true);

        return tokenWithAuthorities;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (AuthenticationToken.class.isAssignableFrom(authentication));
    }


}
