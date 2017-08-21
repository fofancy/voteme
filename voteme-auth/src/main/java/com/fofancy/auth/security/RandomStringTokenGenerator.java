package com.fofancy.auth.security;

import com.fofancy.auth.model.TokenEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Map;

@Component("randomStringTokenGenerator")
public class RandomStringTokenGenerator implements TokenGenerator {
    @Autowired
    private SecureRandom random;

    @Override
    public TokenDto generateToken(Map<String, String> params) {
        TokenDto tokenDto = new TokenDto();
        tokenDto.setTokenValue(nextSessionId());

        return tokenDto;
    }

    public String nextSessionId() {
        return new BigInteger(130, random).toString(32);
    }
}
