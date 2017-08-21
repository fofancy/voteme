package com.fofancy.auth.data;

import com.fofancy.auth.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UsersDaoTest {
    @Autowired
    UsersDao usersDao;

    @Test
    @Transactional
    public void findAllByUsername() throws Exception {
        User asdasd = usersDao.findAllByUsername("asdasd");

        assertNotNull(asdasd);
    }

}