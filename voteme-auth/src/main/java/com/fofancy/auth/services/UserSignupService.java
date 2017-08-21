package com.fofancy.auth.services;

import com.fofancy.auth.data.UsersDao;
import com.fofancy.auth.model.User;
import com.fofancy.auth.model.UserSignupDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by User on 14.07.2017.
 */
@Service
public class UserSignupService {

    @Autowired
    private UsersDao usersDao;

    public UserSignupService() {
    }

    public UsersDao getUsersDao() {
        return usersDao;
    }

    public void setUsersDao(UsersDao usersDao) {
        this.usersDao = usersDao;
    }

    @Transactional
    public void signupUser(UserSignupDto signupDto) {
        User user = new User();
        user.setUsername(signupDto.getUsername());
        user.setEmail(signupDto.getEmail());

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        user.setEncodedPassword(encoder.encode(signupDto.getPassword()));

        usersDao.signupUser(user);
    }
}
