package com.fofancy.auth.services;

import com.fofancy.auth.data.TokensDao;
import com.fofancy.auth.data.UsersDao;
import com.fofancy.auth.model.RoleEntity;
import com.fofancy.auth.model.TokenEntity;
import com.fofancy.auth.model.User;
import com.fofancy.auth.security.TokenDto;
import com.fofancy.auth.security.TokenGenerator;
import com.fofancy.auth.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class DbTokensServiceImpl implements TokenService {
    TokensDao tokensDao;
    UsersDao usersDao;
    TokenGenerator generator;

    @Autowired
    public DbTokensServiceImpl(
            TokensDao tokensDao,
            UsersDao usersDao,
            @Qualifier("randomStringTokenGenerator") TokenGenerator generator
    ) {
        this.tokensDao = tokensDao;
        this.usersDao = usersDao;
        this.generator = generator;
    }

    @Transactional
    public TokenEntity getTokenByValue(String value) {
        return tokensDao.getByTokenValue(value);
    }

    @Override
    @Transactional
    public TokenDto createToken(Map<String, String> params) {
        TokenDto generatedToken = generator.generateToken(params);

        TokenEntity tokenEntity = new TokenEntity();

        User user = usersDao.findAllByUsername(params.get("username"));

        tokenEntity.setUser(user);
        tokenEntity.setTokenValue(generatedToken.getTokenValue());

        insertOrUpdateToken(tokenEntity);

        return generatedToken;
    }

    @Transactional
    public void insertOrUpdateToken(TokenEntity token) {
        List<TokenEntity> tokenByUsername = tokensDao.getByUsername(token.getUser().getUsername());

        if(tokenByUsername.isEmpty())
            tokensDao.addToken(token);
        else
            tokenByUsername.get(0).setTokenValue(token.getTokenValue());
    }

    @Transactional
    public List<SimpleGrantedAuthority> getGrantedAuthoritiesByValue(String tokenValue) {
        TokenEntity tokenEntity = tokensDao.getByTokenValue(tokenValue);

        List<RoleEntity> roles = tokenEntity.getUser().getRoles();

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        for (RoleEntity role: roles) {
            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        }

        return authorities;
    }
}
