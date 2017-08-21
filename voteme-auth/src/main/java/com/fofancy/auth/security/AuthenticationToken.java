package com.fofancy.auth.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class AuthenticationToken extends AbstractAuthenticationToken {

    private String token;
    /**
     * Creates a token with the supplied array of authorities.
     *
     * @param authorities the collection of <tt>GrantedAuthority</tt>s for the principal
     *                    represented by this authentication object.
     */
    public AuthenticationToken(Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
    }

    public AuthenticationToken(String token) {
        super(null);

        this.token = token;
    }

    public AuthenticationToken(String token, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);

        this.token = token;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return this.token;
    }
}
