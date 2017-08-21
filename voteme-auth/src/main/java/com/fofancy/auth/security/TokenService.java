package com.fofancy.auth.security;

import com.fofancy.auth.model.TokenEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Map;

public interface TokenService {
    TokenEntity getTokenByValue(String value);
    TokenDto createToken(Map<String, String> params);
    List<SimpleGrantedAuthority> getGrantedAuthoritiesByValue(String tokenValue);
}
