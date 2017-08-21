package com.fofancy.auth.data;

import com.fofancy.auth.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 * Created by User on 15.07.2017.
 */
@Repository
public class UsersDao {
    @Autowired
    EntityManager em;

    public UsersDao() {
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void signupUser(User user) {
        em.persist(user);
    }

    public User findAllByUsername (String username) {
        TypedQuery<User> findAllQuery = em.createNamedQuery(User.FIND_ALL, User.class);
        findAllQuery.setParameter("username", username);

        return findAllQuery.getSingleResult();
    }

}
