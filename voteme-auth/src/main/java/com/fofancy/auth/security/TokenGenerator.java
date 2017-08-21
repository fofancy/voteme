package com.fofancy.auth.security;

import com.fofancy.auth.model.TokenEntity;

import java.util.Map;

public interface TokenGenerator {

    TokenDto generateToken(Map<String, String> params);
}
