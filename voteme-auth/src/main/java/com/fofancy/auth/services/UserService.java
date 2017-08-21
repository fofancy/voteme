package com.fofancy.auth.services;

import com.fofancy.auth.data.UsersDao;
import com.fofancy.auth.model.RoleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UsersDao usersDao;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.fofancy.auth.model.User user = usersDao.findAllByUsername(username);

        List<SimpleGrantedAuthority> authority = getGrantedAuthorities(user.getRoles());

        User userDetails = new User(user.getUsername(), user.getEncodedPassword(), authority);

        return userDetails;
    }

    private List<SimpleGrantedAuthority> getGrantedAuthorities(List<RoleEntity> roles) {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        for (RoleEntity role: roles) {
            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        }

        return authorities;
    }
}
