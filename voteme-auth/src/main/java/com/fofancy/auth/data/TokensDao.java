package com.fofancy.auth.data;

import com.fofancy.auth.model.TokenEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class TokensDao {
    private EntityManager em;

    @Autowired
    public TokensDao(EntityManager em) {
        this.em = em;
    }

    public void addToken (TokenEntity token) {
        em.persist(token);
    }

    public TokenEntity getByTokenValue(String value) {
        TypedQuery<TokenEntity> query = em.createNamedQuery(TokenEntity.GET_BY_TOKEN_VALUE, TokenEntity.class);
        query.setParameter("tokenValue", value);

        return query.getSingleResult();
    }

    public List<TokenEntity> getByUsername(String username) {
        TypedQuery<TokenEntity> query = em.createNamedQuery(TokenEntity.GET_BY_USERNAME, TokenEntity.class);
        query.setParameter("username", username);

        return query.getResultList();
    }

    public void updateDetachedTokenValue(TokenEntity token, String tokenValue) {
        token.setTokenValue(tokenValue);

        em.merge(token);
    }
}
